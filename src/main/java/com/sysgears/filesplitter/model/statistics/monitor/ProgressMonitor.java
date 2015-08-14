package com.sysgears.filesplitter.model.statistics.monitor;

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
    private final Map<String, ProgressState> progressInfo = new HashMap<String, ProgressState>();

    /**
     * Updates progress info.
     *
     * @param name  part name
     * @param state progress state
     */
    public synchronized void update(final String name, final ProgressState state) {
        progressInfo.put(name, state);
    }

    /**
     * Returns progress info.
     *
     * @return progress info
     */
    public synchronized String getProgressInfo() {
        return progressInfo.toString();
    }
}