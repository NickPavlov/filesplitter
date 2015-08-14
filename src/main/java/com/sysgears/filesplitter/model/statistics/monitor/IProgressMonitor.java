package com.sysgears.filesplitter.model.statistics.monitor;

import com.sysgears.filesplitter.model.statistics.state.IProgressState;

/**
 * The IProgressMonitor interface defines the behavior of a progress monitor object.
 */
public interface IProgressMonitor {

    /**
     * Updates current progress information.
     *
     * @param name name of an object
     * @param state object's state
     */
    public void update(final String name, final IProgressState state);

    /**
     * Returns progress information.
     *
     * @return progress information
     */
    public String getProgressInfo();
}