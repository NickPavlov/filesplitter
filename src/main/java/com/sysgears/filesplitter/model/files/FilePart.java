package com.sysgears.filesplitter.model.files;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

/**
 * The FilePart class provides functionality to create a part of the file.
 */
public class FilePart implements IDataProcessor {

    /**
     * The file part name.
     */
    private final String partName;

    /**
     * The file part size.
     */
    private final long partSize;

    /**
     * Creates the FilePart object specified by part name and size.
     *
     * @param partName file part name
     * @param partSize final part size
     */
    public FilePart(String partName, long partSize) {
        this.partName = partName;
        this.partSize = partSize;
    }

    /**
     * Returns a name of the part of the file.
     *
     * @return file part name
     */
    public String getName() {
        return partName;
    }

    /**
     * Initiates the process of file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully, false otherwise
     */
    public boolean process(final IData originalFile) {
        return false;
    }
}