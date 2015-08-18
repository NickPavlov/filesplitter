package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
import com.sysgears.filesplitter.view.IUserInterface;

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

    }
}
