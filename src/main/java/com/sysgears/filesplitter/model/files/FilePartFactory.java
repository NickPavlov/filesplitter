package com.sysgears.filesplitter.model.files;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.abstractmodel.Worker;

/**
 * The FilePartFactory class provides functionality to create workers.
 */
public class FilePartFactory {

    /**
     * Original file.
     */
    private final IData originalFile;

    /**
     * Part creator.
     */
    private final IDataProcessor partCreator;

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
    public FilePartFactory(final IData originalFile, final IDataProcessor partCreator, final long partSize) {
        this.originalFile = originalFile;
        this.partCreator = partCreator;
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