package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.filesystem.directory.Directory;
import com.sysgears.filesplitter.model.filesystem.directory.IDirectory;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartCreatorsFactory;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartWorkersFactory;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.statistics.ProgressMonitor;
import com.sysgears.filesplitter.view.IUserInterface;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * The FileSplitService class provides functionality to split a file into parts.
 */
public class FileSplitService implements Runnable {

    /**
     * Pool of threads.
     */
    private final ExecutorService pool;

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * File finder.
     */
    private final IDataFinder fileFinder;

    /**
     * Command line parser.
     */
    private final CmdLineParser cmdLineParser;

    /**
     * Split options.
     */
    private final SplitOptions splitOptions;

    /**
     * Command line arguments.
     */
    private final String[] args;

    /**
     * Creates the FileSplitService instance.
     *
     * @param pool pool of threads
     * @param ui   user interface
     * @throws IllegalArgumentException if user interface is null
     */
    public FileSplitService(final ExecutorService pool,
                            final IUserInterface ui,
                            final IDataFinder fileFinder,
                            final CmdLineParser cmdLineParser,
                            final SplitOptions splitOptions,
                            final String[] args) {

        this.pool = pool;
        this.ui = ui;
        this.fileFinder = fileFinder;
        this.cmdLineParser = cmdLineParser;
        this.splitOptions = splitOptions;
        this.args = args;
    }

    /**
     * Starts the service.
     */
    public void run() {
        //SplitOptions splitOptions = new SplitOptions();
        //CmdLineParser cmdLineParser = new CmdLineParser(splitOptions);

        try {
            cmdLineParser.parseArgument(args);

            System.out.println("Path: " + splitOptions.getFilePath());
            System.out.println("PartSize: " + splitOptions.getPartSize());
            System.out.println("MB: " + splitOptions.isMegabytes());
            System.out.println("kB: " + splitOptions.isKilobytes());

            int partSize = splitOptions.getPartSize();
            if (splitOptions.isMegabytes()) {
                partSize *= MemoryUnits.MEGABYTE;
            } else if (splitOptions.isKilobytes()) {
                partSize *= MemoryUnits.KILOBYTE;
            }

            final String filePath = splitOptions.getFilePath();
            final IData file = fileFinder.getByName(filePath);
            final IDirectory partsDirectory = new Directory(filePath).appendInnerDirectory(file.getName() + "_parts");
            final ProgressMonitor progressMonitor = new ProgressMonitor();
            final PartCreatorsFactory partCreator =
                    new PartCreatorsFactory(partSize, partsDirectory.getAbsolutePath(), progressMonitor);
            final PartWorkersFactory workerFactory = new PartWorkersFactory(file);
            new Thread(new ProgressInfoService(ui, progressMonitor, pool, 10)).start();
            System.out.println();
            for (int i = 0; i < file.getSize() / partSize + 1; ++i) {
                pool.execute(workerFactory.create(partCreator.create()));
                System.out.println("thread-" + i);
            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CmdLineException e) {
            cmdLineParser.printUsage(System.out);
        }

    }
}