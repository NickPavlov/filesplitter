package com.sysgears.filesplitter.model.filesystem.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.abstractmodel.Worker;

/**
 * The WorkersFactory class provides functionality to create workers.
 */
public class WorkersFactory {

    /**
     * Original file.
     */
    private final IData data;

    /**
     * Workers count.
     */
    private int workersCount;

    /**
     * Creates the WorkersFactory instance.
     *
     * @param data data
     */
    public WorkersFactory(final IData data) {
        this.data = data;
        this.workersCount = 0;
    }

    /**
     * Creates a new worker object.
     *
     * @return worker
     */
    public Worker create(final IDataProcessor processor) {
        return new Worker("worker-" + workersCount++, data, processor);
    }
}