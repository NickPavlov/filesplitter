package com.sysgears.filesplitter.model.abstractmodel;

/**
 * Provides a data processing.
 */
public abstract class AbstractWorker implements Runnable {

    /**
     * Process a data.
     */
    public abstract void run();
}