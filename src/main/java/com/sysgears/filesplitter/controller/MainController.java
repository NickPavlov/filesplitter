package com.sysgears.filesplitter.controller;

import com.sysgears.filesplitter.services.IService;
import com.sysgears.filesplitter.model.console.commands.Commands;
import com.sysgears.filesplitter.model.console.options.BuildOptions;
import com.sysgears.filesplitter.model.console.options.SplitOptions;
import com.sysgears.filesplitter.model.util.StringCreator;
import com.sysgears.filesplitter.view.IUserInterface;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The MainController class is the main controller that controls the application.
 */
public class MainController implements IController {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(MainController.class);

    /**
     * FileSplitService instance.
     */
    private final IService fileSplitService;

    /**
     * FileBuildService instance.
     */
    private final IService fileBuildService;

    /**
     * Progress info service.
     */
    private final IService progressInfoService;

    /**
     * Command line split options.
     */
    private final SplitOptions splitCmdOptions;

    /**
     * Command line build options.
     */
    private final BuildOptions buildCmdOptions;

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * Creates the MainController instance.
     *
     * @param fileSplitService    FileSplitService
     * @param fileCreateService   FileCreateService
     * @param splitCmdOptions     split command line arguments options
     * @param buildCmdOptions     build command line arguments options
     * @param progressInfoService progress info service
     */
    public MainController(final IService fileSplitService,
                          final IService fileCreateService,
                          final IService progressInfoService,
                          final SplitOptions splitCmdOptions,
                          final BuildOptions buildCmdOptions,
                          final IUserInterface userInterface) {

        this.fileSplitService = fileSplitService;
        this.fileBuildService = fileCreateService;
        this.progressInfoService = progressInfoService;
        this.splitCmdOptions = splitCmdOptions;
        this.buildCmdOptions = buildCmdOptions;
        this.ui = userInterface;
    }

    /**
     * Starts the controller.
     *
     * @param args command line arguments
     */
    public void start(final String[] args) {
        try {
            if (args.length == 0) {
                ui.sendMessage(StringCreator.createFromCollection(Commands.getCommandsList()));
                return;
            }
            Commands command = Commands.getCommand(args[0]);
            if (command == Commands.UNKNOWN_COMMAND) {
                ui.sendMessage(StringCreator.createFromCollection(Commands.getCommandsList()));
            } else {
                String[] options = new String[args.length - 1];
                System.arraycopy(args, 1, options, 0, args.length - 1);
                new Thread(progressInfoService).start();

                switch (command) {
                    case SPLIT:
                        try {
                            new CmdLineParser(splitCmdOptions).parseArgument(options);

                            // only for testing
                            ui.sendMessage("\n");
                            ui.sendMessage("Path: " + splitCmdOptions.getFilePath() + "\n");
                            ui.sendMessage("PartSize: " + splitCmdOptions.getPartSize() + "\n");
                            ui.sendMessage("MB: " + splitCmdOptions.isMegabytes() + "\n");
                            ui.sendMessage("kB: " + splitCmdOptions.isKilobytes() + "\n");

                            fileSplitService.start();
                        } catch (CmdLineException e) {
                            log.error(e.getMessage());
                            e.getParser().printUsage(ui.getOutputStream());
                        } finally {
                            fileSplitService.stop();
                        }
                        break;
                    case BUILD:
                        try {
                            new CmdLineParser(buildCmdOptions).parseArgument(options);
                            fileBuildService.start();
                        } catch (CmdLineException e) {
                            log.error(e.getMessage());
                            e.getParser().printUsage(ui.getOutputStream());
                        } finally {
                            fileBuildService.stop();
                        }
                        break;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Stops the controller.
     */
    public void stop() {
    }
}