package com.sysgears.filesplitter.model.worker;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The Worker class provides functionality for data processing.
 */
public class Worker implements Runnable {

    /**
     * Logger.
     */
    private final static Logger log = Logger.getLogger(Worker.class);

    /**
     * Worker's name.
     */
    private final String name;

    /**
     * Data to process.
     */
    private final IData data;

    /**
     * Data processor.
     */
    private final IDataProcessor dataProcessor;

    /**
     * Creates the Worker instance specified by data and data processor.
     *
     * @param name          worker's name
     * @param data          data
     * @param dataProcessor data processor
     */
    public Worker(final String name, final IData data, final IDataProcessor dataProcessor) {
        this.name = name;
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
            log.error("<" + name + "> " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}