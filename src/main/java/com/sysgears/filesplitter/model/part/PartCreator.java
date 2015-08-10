package com.sysgears.filesplitter.model.part;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.IOException;

/**
 * The PartCreator class provides functionality to create a part of the file.
 */
public class PartCreator implements IDataProcessor {

    /**
     * The file part name.
     */
    private String partName;

    /**
     * The file part size.
     */
    private long partSize;

    /**
     * Creates the PartCreator object specified by part name and size.
     *
     * @param partName file part name
     * @param partSize final part size
     */
    public PartCreator(final String partName, final long partSize) {
        this.partName = partName;
        this.partSize = partSize;
    }

    /**
     * Initiates the process of file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully, false otherwise
     * @throws IOException in case of data access error
     */
    public boolean process(final IData originalFile) throws IOException {
        System.out.println("Part name: " + partName);
        System.out.println("Part size: " + partSize);
        System.out.println("File name: " + originalFile.getName());

        return false;
    }

    /**
     * Sets a part name.
     *
     * @param partName part name
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * Sets part size.
     *
     * @param partSize part size
     */
    public void setPartSize(long partSize) {
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
     * Returns part size.
     *
     * @return part size
     */
    public long getPartSize() {
        return partSize;
    }

}