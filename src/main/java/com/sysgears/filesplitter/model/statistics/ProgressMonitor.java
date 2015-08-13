package com.sysgears.filesplitter.model.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * The ProgressMonitor class provides functionality to gather information about the work done.
 */
public class ProgressMonitor {

    /**
     * Progress info.
     */
    private final Map<String, ProgressState> progressInfo = new HashMap<String, ProgressState>();

    /**
     * Updates progress info.
     *
     * @param partName part name
     * @param state    progress state
     */
    public synchronized void update(final String partName, final ProgressState state) {
        progressInfo.put(partName, state);
    }

    /**
     * Returns progress info.
     *
     * @return progress info
     */
    public synchronized Map<String, ProgressState> getProgressInfo() {
        return new HashMap<String, ProgressState>(progressInfo);
    }
}