package com.sysgears.filesplitter.model.statistics.monitor;

import com.sysgears.filesplitter.model.statistics.state.IProgressState;

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
     * Updates progress info.
     *
     * @param name  part name
     * @param state progress state
     */
    public synchronized void update(final String name, final IProgressState state) {
        progressInfo.put(name, state);
    }

    /**
     * Returns progress info.
     *
     * @return progress info
     */
    public synchronized Map<String, IProgressState> getProgressInfo() {
        return new HashMap<String, IProgressState>(progressInfo);
    }
}