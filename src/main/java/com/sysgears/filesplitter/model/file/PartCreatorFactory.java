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
     * Output directory.
     */
    private final String outputDirectory;

    /**
     * Creates the PartCreatorFactory object specified by part size.
     *
     * @param partSize part size
     * @param outputDirectory output directory
     */
    public PartCreatorFactory(final long partSize, final String outputDirectory) {
        this.partSize = partSize;
        this.partNumber = 0;
        this.outputDirectory = outputDirectory;
    }

    /**
     * Creates new PartCreator object.
     *
     * @return PartCreator object
     */
    public PartCreator create() {
        return new PartCreator("part_" + partNumber++, partSize, outputDirectory);
    }
}