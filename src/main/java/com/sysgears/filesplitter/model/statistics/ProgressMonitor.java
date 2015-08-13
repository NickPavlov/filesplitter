package com.sysgears.filesplitter.model.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * The ProgressMonitor class provides functionality to gather information about the work done.
 */
public class ProgressMonitor {

    private final Map<String, String> progressInfo = new HashMap<String, String>();


    public synchronized void update(final String partNumber, final String progress) {

    }
}