package com.sysgears.filesplitter.model.file;

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
     * Number of parts.
     */
    private int partCount;

    /**
     * Creates the FilePartFactory object specified by the part size.
     *
     * @param originalFile original file
     */
    public FilePartFactory(final IData originalFile) {
        this.originalFile = originalFile;
    }

    /**
     * Creates new worker object.
     *
     * @return worker
     */
    public Worker create(final IDataProcessor partCreator) {
        return null;
    }
}