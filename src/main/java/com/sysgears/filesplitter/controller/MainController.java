package com.sysgears.filesplitter.controller;

import com.sysgears.filesplitter.model.consoleoptions.BuildOptions;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * The MainController class is the main controller that controls the application.
 */
public class MainController implements IController {

    /**
     * FileSplitService instance.
     */
    private final Runnable fileSplitService;

    /**
     * FileBuildService instance.
     */
    private final Runnable fileBuildService;

    /**
     * Progress info service.
     */
    private final Runnable progressInfoService;

    /**
     * Command line split options.
     */
    private final SplitOptions splitCmdOptions;

    /**
     * Command line build options.
     */
    private final BuildOptions buildCmdOptions;

    /**
     * Creates the MainController instance.
     *
     * @param fileSplitService    FileSplitService
     * @param fileCreateService   FileCreateService
     * @param splitCmdOptions     split command line arguments options
     * @param buildCmdOptions     build command line arguments options
     * @param progressInfoService progress info service
     */
    public MainController(final Runnable fileSplitService,
                          final Runnable fileCreateService,
                          final Runnable progressInfoService,
                          final SplitOptions splitCmdOptions,
                          final BuildOptions buildCmdOptions) {

        this.fileSplitService = fileSplitService;
        this.fileBuildService = fileCreateService;
        this.progressInfoService = progressInfoService;
        this.splitCmdOptions = splitCmdOptions;
        this.buildCmdOptions = buildCmdOptions;
    }

    /**
     * Starts the controller.
     *
     * @param args command line arguments
     */
    public void start(final String[] args) {
        try {

            new CmdLineParser(splitCmdOptions).parseArgument(args);
            new Thread(progressInfoService).start();

            //fileSplitService.run();
            fileBuildService.run();

        } catch (CmdLineException e) {
            e.getParser().printUsage(System.out);
        }
    }

    /**
     * Stops the controller.
     */
    public void stop() {
    }
}