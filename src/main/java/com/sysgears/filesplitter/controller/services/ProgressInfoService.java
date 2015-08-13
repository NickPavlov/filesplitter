package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.statistics.ProgressMonitor;
import com.sysgears.filesplitter.view.IUserInterface;

import java.util.concurrent.ExecutorService;

/**
 * The ProgressInfoService class provides functionality to display progress.
 */
public class ProgressInfoService implements Runnable {

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * Progress monitor.
     */
    private final ProgressMonitor progressMonitor;

    /**
     * Pool of threads.
     */
    private final ExecutorService pool;

    /**
     * The interval between updates.
     */
    private final int updateInterval;

    /**
     * Creates the ProgressInfoService instance specified by:
     * user interface, progress monitor, poll of threads, update interval.
     *
     * @param ui              user interface
     * @param progressMonitor progress monitor
     * @param pool            pool of threads
     * @param updateInterval  interval between updates
     */
    public ProgressInfoService(final IUserInterface ui,
                               final ProgressMonitor progressMonitor,
                               final ExecutorService pool,
                               final int updateInterval) {
        this.ui = ui;
        this.progressMonitor = progressMonitor;
        this.pool = pool;
        this.updateInterval = updateInterval;
    }

    /**
     * Starts the service.
     */
    public void run() {
        while (!pool.isTerminated()) {
            System.out.println(progressMonitor.getProgressInfo());
            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
                //
            }
        }
    }
}