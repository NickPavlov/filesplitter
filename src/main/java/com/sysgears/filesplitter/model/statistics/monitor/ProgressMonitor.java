package com.sysgears.filesplitter.model.statistics.monitor;

import com.sysgears.filesplitter.model.statistics.state.IProgressState;
import com.sysgears.filesplitter.model.statistics.state.ProgressState;

import java.util.HashMap;
import java.util.Map;

/**
 * The ProgressMonitor class provides functionality to gather information about the work done.
 */
public class ProgressMonitor implements IProgressMonitor {

    /**
     * Progress info.
     */
    private final Map<String, IProgressState> progressInfo = new HashMap<String, IProgressState>();

    /**
     * Registers a new worker.
     * Returns true if worker is not registered.
     *
     * @param name  worker's name
     * @param total total value
     * @return true if worker is registered, false otherwise
     */
    public synchronized boolean register(final String name, final long total) {
        boolean registered = false;
        if (progressInfo.containsKey(name)) {
            progressInfo.put(name, new ProgressState(0, total));
            registered = true;
        }

        return registered;
    }

    /**
     * Updates progress info.
     *
     * @param name    part name
     * @param current current value
     */
    public synchronized void update(final String name, final long current) {

    }

    /**
     * Returns progress info.
     *
     * @return progress info
     */
    public synchronized Map<String, IProgressState> getProgressInfo() {
        return new HashMap<>(progressInfo);
    }
}