package com.sysgears.filesplitter.model.part;

/**
 * The PartCreatorsFactory class provides functionality to create PartCreator instances.
 */
public class PartCreatorsFactory {

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
     * Creates the PartCreatorsFactory object specified by part size.
     *
     * @param partSize        part size
     * @param outputDirectory output directory
     */
    public PartCreatorsFactory(final long partSize, final String outputDirectory) {
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
        return new PartCreator(partNumber++, partSize, outputDirectory);
    }
}