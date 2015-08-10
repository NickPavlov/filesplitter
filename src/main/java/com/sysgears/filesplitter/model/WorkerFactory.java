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
    private final IData originalFile;

    /**
     * Number of parts.
     */
    private int partCount;

    /**
     * Creates the WorkerFactory object specified by the part size.
     *
     * @param originalFile original file
     */
    public WorkerFactory(final IData originalFile) {
        this.originalFile = originalFile;
    }

    /**
     * Creates a new worker object.
     *
     * @return worker
     */
    public Worker create(final IDataProcessor partCreator) {
        return new Worker(originalFile, partCreator);
    }
}