package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
import com.sysgears.filesplitter.model.statistics.state.IProgressState;
import com.sysgears.filesplitter.view.IUserInterface;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * The ProgressInfoService class provides functionality to display progress.
 */
public class ProgressInfoService implements IService {

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * Progress monitor.
     */
    private final IProgressMonitor progressMonitor;

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
                               final IProgressMonitor progressMonitor,
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
    public void start() {
        try {
            while (!pool.isTerminated()) {
                sendProgressMessage(progressMonitor.getProgressInfo(), ui);
                Thread.sleep(updateInterval);
            }
            sendProgressMessage(progressMonitor.getProgressInfo(), ui);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops a service.
     */
    public void stop() {
        pool.shutdown();
    }

    /**
     * Starts a service. It behaves exactly like method <code>start()</code>.
     */
    public void run() {
        start();
    }

    private void sendProgressMessage(final Map<String, IProgressState> progressState, final IUserInterface ui) {
        if ((progressState != null) && !progressState.isEmpty()) {
            System.out.println(progressState);
        }
    }
}