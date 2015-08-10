package com.sysgears.filesplitter.model.file;

/**
 * The PartCreatorFactory class provides functionality to create PartCreator instances.
 */
public class PartCreatorFactory {

    /**
     * Part size.
     */
    private final long partSize;

    /**
     * Part number.
     */
    private int partNumber;

    /**
     * Creates the PartCreatorFactory object specified by part size.
     *
     * @param partSize part size
     */
    public PartCreatorFactory(final long partSize) {
        this.partSize = partSize;
        this.partNumber = 0;
    }

    /**
     * Creates new PartCreator object.
     *
     * @return PartCreator object
     */
    public PartCreator create() {
        return new PartCreator("part_" + partNumber++, partSize);
    }
}