package com.sysgears.filesplitter.model;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.abstractmodel.Worker;

/**
 * The WorkerFactory class provides functionality to create workers.
 */
public class WorkerFactory {

    /**
     * Original file.
     */
    private final IData data;

    /**
     * Creates the WorkerFactory object.
     *
     * @param data data
     */
    public WorkerFactory(final IData data) {
        this.data = data;
    }

    /**
     * Creates a new worker object.
     *
     * @return worker
     */
    public Worker create(final IDataProcessor processor) {
        return new Worker(data, processor);
    }
}