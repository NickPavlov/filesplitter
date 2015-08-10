package com.sysgears.filesplitter.model.abstractmodel;

import java.io.IOException;

/**
 * The Worker class provides functionality for data processing.
 */
public class Worker implements Runnable {

    /**
     * Data to process.
     */
    private final IData data;

    /**
     * Data processor.
     */
    private final IDataProcessor dataProcessor;

    /**
     * Creates the Worker object specified by data and data processor.
     *
     * @param data          data
     * @param dataProcessor data processor
     */
    public Worker(final IData data, final IDataProcessor dataProcessor) {
        this.data = data;
        this.dataProcessor = dataProcessor;
    }

    /**
     * Performs data processing.
     */
    public void run() {
        try {
            dataProcessor.process(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}