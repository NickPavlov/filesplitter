package com.sysgears.filesplitter.model.statistics.monitor;

import com.sysgears.filesplitter.model.statistics.state.IProgressState;

import java.util.Map;

/**
 * The IProgressMonitor interface defines the behavior of a progress monitor object.
 */
public interface IProgressMonitor {

    /**
     * Registers a new worker.
     * Returns true if worker is not registered.
     *
     * @param name  worker's name
     * @param total total value
     * @return true if worker is registered, false otherwise
     */
    public boolean register(final String name, final long total);

    /**
     * Updates progress information.
     *
     * @param name    name of an object
     * @param current current value
     */
    public void update(final String name, final long current);

    /**
     * Returns progress information.
     *
     * @return progress information
     */
    public Map<String, IProgressState> getProgressInfo();
}