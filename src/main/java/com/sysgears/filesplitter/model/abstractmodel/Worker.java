package com.sysgears.filesplitter.model.abstractmodel;

/**
 * Provides a data processing.
 */
public abstract class Worker implements Runnable {

    /**
     * Process a data.
     */
    public abstract void run();
}