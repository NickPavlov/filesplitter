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
     * Creates the PartCreatorFactory object specified by part size.
     *
     * @param partSize part size
     */
    public PartCreatorFactory(final long partSize) {
        this.partSize = partSize;
    }

    /**
     * Creates new PartCreator object.
     *
     * @return PartCreator object
     */
    public PartCreator create() {
        return null;
    }
}