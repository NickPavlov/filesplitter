package com.sysgears.filesplitter.model.worker;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

/**
 * The StaticDataWorkersFactory class provides functionality to create worker.
 */
public class WorkersFactory {

    /**
     * Workers count.
     */
    private int workersCount = 0;

    /**
     * Creates a new worker object.
     *
     * @return worker
     */
    public Worker create(final IData data, final IDataProcessor processor) {
        return new Worker("worker-" + workersCount++, data, processor);

    }
}