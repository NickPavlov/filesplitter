package com.sysgears.filesplitter.model.workers;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.abstractmodel.Worker;

/**
 * The StaticDataWorkersFactory class provides functionality to create workers.
 */
public class StaticDataWorkersFactory {

    /**
     * Original file.
     */
    private final IData data;

    /**
     * Workers count.
     */
    private int workersCount;

    /**
     * Creates the StaticDataWorkersFactory instance.
     *
     * @param data data
     */
    public StaticDataWorkersFactory(final IData data) {
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