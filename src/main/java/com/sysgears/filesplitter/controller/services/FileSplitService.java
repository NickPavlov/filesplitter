package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.filesystem.directory.Directory;
import com.sysgears.filesplitter.model.filesystem.directory.IDirectory;
import com.sysgears.filesplitter.model.filesystem.file.FileFinder;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartCreatorsFactory;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartWorkersFactory;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.view.IUserInterface;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * The FileSplitService class provides functionality to split a file into parts.
 */
public class FileSplitService implements IService {

    /**
     * Pool of threads.
     */
    private final ExecutorService pool;

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * Creates the FileSplitService instance specified by the pool and user interface.
     *
     * @param pool pool of threads
     * @param ui   user interface
     * @throws IllegalArgumentException if user interface is null
     */
    public FileSplitService(final ExecutorService pool, final IUserInterface ui) {
        if (pool == null) {
            throw new IllegalArgumentException("Pool of threads can't be null.");
        }
        if (ui == null) {
            throw new IllegalArgumentException("User interface can't be null.");
        }
        this.pool = pool;
        this.ui = ui;
    }

    /**
     * Starts a controller.
     *
     * @param args console arguments
     */
    public void start(final String[] args) {
        SplitOptions splitOptions = new SplitOptions();
        CmdLineParser cmdLineParser = new CmdLineParser(splitOptions);
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
            final IData file = new FileFinder().getByName(filePath);

            final IDirectory partsDirectory = new Directory(filePath)
                    .appendInnerDirectory(file.getName() + "_parts");

            final PartCreatorsFactory partCreator = new PartCreatorsFactory(partSize, partsDirectory.getAbsolutePath());
            final PartWorkersFactory workerFactory = new PartWorkersFactory(file);

            System.out.println();
            for (int i = 0; i < file.getSize() / partSize + 1; ++i) {
                pool.execute(workerFactory.create(partCreator.create()));
                System.out.println("thread-" + i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CmdLineException e) {
            cmdLineParser.printUsage(System.out);
        }
        pool.shutdown();
    }

    /**
     * Stops a controller
     */
    public void stop() {
    }
}