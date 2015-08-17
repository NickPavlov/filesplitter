package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.filesystem.directory.Directory;
import com.sysgears.filesplitter.model.filesystem.directory.IDirectory;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartCreatorsFactory;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartWorkersFactory;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.partiterator.IPartIterator;
import com.sysgears.filesplitter.model.partiterator.PartIterator;
import com.sysgears.filesplitter.model.statistics.monitor.ProgressMonitor;
import com.sysgears.filesplitter.view.IUserInterface;

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
     * Split options.
     */
    private final SplitOptions splitOptions;

    /**
     * Progress monitor.
     */
    private final ProgressMonitor progressMonitor;

   /**
     * Creates the FileSplitService instance.
     *
     * @param pool                pool of threads
     * @param ui                  user interface
     * @param fileFinder          file finder
     * @param splitOptions        command line options
     * @param progressMonitor     progress info monitor
     */
    public FileSplitService(final ExecutorService pool,
                            final IUserInterface ui,
                            final IDataFinder fileFinder,
                            final SplitOptions splitOptions,
                            final ProgressMonitor progressMonitor) {
        this.ui = ui;
        this.pool = pool;
        this.fileFinder = fileFinder;
        this.splitOptions = splitOptions;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Starts the service.
     */
    public void run() {
        try {
            System.out.println();
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
            final PartCreatorsFactory partCreator =
                    new PartCreatorsFactory(partSize, partsDirectory.getAbsolutePath(), progressMonitor);
            final PartWorkersFactory workerFactory = new PartWorkersFactory(file);
            System.out.println();
            IPartIterator partIterator = new PartIterator(file.getSize(), partSize);
            int i = 0;
            while (partIterator.hasNext()) {
                pool.execute(workerFactory.create(partCreator.create()));
                partIterator.nextPartSize();
                System.out.println("worker-" + i++);
            }

            System.out.println();
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}