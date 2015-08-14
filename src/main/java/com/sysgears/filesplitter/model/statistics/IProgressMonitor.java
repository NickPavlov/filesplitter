package com.sysgears.filesplitter.model.statistics;

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
    public void update(final String name, final ProgressState state);

    /**
     * Returns progress information.
     *
     * @return progress information
     */
    public String getProgressInfo();
}