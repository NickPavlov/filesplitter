package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataIterator;
import com.sysgears.filesplitter.model.abstractmodel.Worker;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.filesystem.file.FileData;
import com.sysgears.filesplitter.model.filesystem.file.FileFinder;
import com.sysgears.filesplitter.model.filesystem.file.filebuilder.FileBuilder;
import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
import com.sysgears.filesplitter.model.workers.StaticDataWorkersFactory;
import com.sysgears.filesplitter.view.IUserInterface;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * The FileBuildService class provides functionality to create file form parts.
 */
public class FileBuildService implements Runnable {

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
     * Creates the FileBuildService instance.
     *
     * @param pool            pool of threads
     * @param ui              user interface
     * @param splitOptions    command line options
     * @param progressMonitor progress info monitor
     */
    public FileBuildService(final ExecutorService pool,
                            final IUserInterface ui,
                            final SplitOptions splitOptions,
                            final IProgressMonitor progressMonitor) {
        this.ui = ui;
        this.pool = pool;
        this.splitOptions = splitOptions;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Starts a service.
     */
    public void run() {
        try {
            IDataIterator fileIterator = new FileFinder("/home/nick/Documents/jdk.tar.gz_parts").iterator();
            FileData fileData = new FileData(new File("/home/nick/Documents/jdk.tar.gz_parts/restored.tar.gz"));
            StaticDataWorkersFactory factory = new StaticDataWorkersFactory(fileData);
            IData filePart;
            int i = 0;
            while (fileIterator.hasNext()) {
                filePart = fileIterator.next();
                pool.execute(new Worker("worker-" + i++, filePart, new FileBuilder(fileData)));

                /*
                System.out.println("File name: " + filePart.getName());
                System.out.println("File size: " + filePart.getSize());
                System.out.println("");
                */

            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
