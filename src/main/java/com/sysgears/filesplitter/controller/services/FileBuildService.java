package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.statistics.monitor.ProgressMonitor;
import com.sysgears.filesplitter.view.IUserInterface;

import java.util.concurrent.ExecutorService;

/**
 * The FileBuildService class provides functionality to create file form parts.
 */
public class FileBuildService implements Runnable {


    public FileBuildService(final ExecutorService pool,
                            final IUserInterface ui,
                            final IDataFinder fileFinder,
                            final SplitOptions splitOptions,
                            final ProgressMonitor progressMonitor) {
    }

    /**
     * Starts a service.
     */
    public void run() {

    }
}
