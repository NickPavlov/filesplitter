package com.sysgears.filesplitter.model.part;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.abstractmodel.Worker;

/**
 * The PartWorkersFactory class provides functionality to create workers.
 */
public class PartWorkersFactory {

    /**
     * Original file.
     */
    private final IData data;

    /**
     * Creates the PartWorkersFactory object.
     *
     * @param data data
     */
    public PartWorkersFactory(final IData data) {
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