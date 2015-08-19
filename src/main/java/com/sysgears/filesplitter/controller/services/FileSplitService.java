package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.console.options.SplitOptions;
import com.sysgears.filesplitter.model.filesystem.directory.Directory;
import com.sysgears.filesplitter.model.filesystem.directory.IDirectory;
import com.sysgears.filesplitter.model.filesystem.file.FileFinder;
import com.sysgears.filesplitter.model.filesystem.file.partcreator.PartCreatorsFactory;
import com.sysgears.filesplitter.model.workers.StaticDataWorkersFactory;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.math.partiterator.IPartIterator;
import com.sysgears.filesplitter.model.math.partiterator.PartIterator;
import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
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
     * Split options.
     */
    private final SplitOptions splitOptions;

    /**
     * Progress monitor.
     */
    private final IProgressMonitor progressMonitor;

    /**
     * Creates the FileSplitService instance.
     *
     * @param pool            pool of threads
     * @param ui              user interface
     * @param splitOptions    command line options
     * @param progressMonitor progress info monitor
     */
    public FileSplitService(final ExecutorService pool,
                            final IUserInterface ui,
                            final SplitOptions splitOptions,
                            final IProgressMonitor progressMonitor) {
        this.ui = ui;
        this.pool = pool;
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

            int userPartSize = splitOptions.getPartSize();
            if (splitOptions.isMegabytes()) {
                userPartSize *= MemoryUnits.MEGABYTE;
            } else if (splitOptions.isKilobytes()) {
                userPartSize *= MemoryUnits.KILOBYTE;
            }
            System.out.println("partSize: " + userPartSize);

            final String filePath = splitOptions.getFilePath();
            final IData file = new FileFinder().getByName(filePath);
            final IDirectory partsDirectory = new Directory(filePath).appendInnerDirectory(file.getName() + "_parts");
            final PartCreatorsFactory partCreator =
                    new PartCreatorsFactory(userPartSize, partsDirectory.getAbsolutePath(), progressMonitor);
            final StaticDataWorkersFactory workerFactory = new StaticDataWorkersFactory(file);
            final IPartIterator partIterator = new PartIterator(file.getSize(), userPartSize);
            while (partIterator.hasNext()) {
                pool.execute(workerFactory.create(partCreator.create(partIterator.next())));
            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}