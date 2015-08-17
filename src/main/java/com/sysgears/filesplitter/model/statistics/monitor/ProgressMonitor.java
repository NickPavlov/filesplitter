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
     * Total progress info.
     */
    private final Map<String, Long> totalProgress = new HashMap<>();


    /**
     * Current progress info.
     */
    private final Map<String, Long> currentProgress = new HashMap<>();

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
        if (!totalProgress.containsKey(name)) {
            totalProgress.put(name, total);
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
    public synchronized boolean update(final String name, final long current) {
        boolean updated = false;
        if (totalProgress.containsKey(name)) {
            currentProgress.put(name, current);
            updated = true;
        }

        return updated;
    }

    /**
     * Returns progress info.
     *
     * @return progress info
     */
    public synchronized Map<String, IProgressState> getProgressInfo() {
        Map<String, IProgressState> result = new HashMap<>();
        Long currentState;
        String key;
        for (Map.Entry<String, Long> entry : totalProgress.entrySet()) {
            key = entry.getKey();
            currentState = currentProgress.containsKey(key) ? currentProgress.get(key) : 0L;
            result.put(key, new ProgressState(currentState, totalProgress.get(key)));
        }

        return result;
    }
}