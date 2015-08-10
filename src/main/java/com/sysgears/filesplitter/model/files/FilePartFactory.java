package com.sysgears.filesplitter.model.files;

import com.sysgears.filesplitter.model.abstractmodel.Worker;

/**
 * The FilePartFactory class provides functionality to create workers.
 */
public class FilePartFactory {

    /**
     * Part size.
     */
    private final long partSize;

    /**
     * Number of parts.
     */
    private int partCount;

    /**
     * Creates the FilePartFactory object specified by the part size.
     *
     * @param partSize part size
     */
    public FilePartFactory(final long partSize) {
        this.partSize = partSize;
    }

    /**
     * Creates new worker object.
     *
     * @return worker
     */
    public Worker create() {
        return null;
    }
}