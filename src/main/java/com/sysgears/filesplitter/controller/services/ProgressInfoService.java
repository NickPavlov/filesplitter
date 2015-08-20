package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
import com.sysgears.filesplitter.model.statistics.state.IProgressState;
import com.sysgears.filesplitter.view.IUserInterface;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * The ProgressInfoService class provides functionality to display progress.
 */
public class ProgressInfoService implements IService {

    /**
     * Logger.
     */
    private final static Logger LOG = Logger.getLogger(ProgressInfoService.class);

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
     * Running state.
     */
    private boolean isRunning;

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
        this.pool = pool;
        this.progressMonitor = progressMonitor;
        this.updateInterval = updateInterval;
    }

    /**
     * Starts the service.
     */
    public void start() {
        isRunning = true;
        LOG.info("ProgressInfoService started.");
        try {
            while (!pool.isTerminated() && isRunning) {
                sendProgressInfo(progressMonitor.getProgressInfo(), ui);
                Thread.sleep(updateInterval);
            }
            sendProgressInfo(progressMonitor.getProgressInfo(), ui);
        } catch (IOException | InterruptedException e) {
            LOG.error(e.getMessage());
        }
        LOG.info("ProgressInfoService stopped.");
    }

    /**
     * Stops a service.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Starts a service. It behaves exactly like the method <code>start()</code>.
     */
    public void run() {
        start();
    }

    /**
     * Sends progress info to the user interface.
     *
     * @param progress progress state
     * @param ui       user interface
     * @throws IOException if an I/O error occurred
     */
    private void sendProgressInfo(final Map<String, IProgressState> progress,
                                  final IUserInterface ui) throws IOException {

        if ((progress != null) && !progress.isEmpty()) {
            ui.sendMessage(progress.toString() + "\n");
        }
    }
}