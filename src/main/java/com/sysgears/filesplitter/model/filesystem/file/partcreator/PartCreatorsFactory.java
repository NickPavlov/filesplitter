package com.sysgears.filesplitter.model.filesystem.file.partcreator;

import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;

/**
 * The PartCreatorsFactory class provides functionality to create PartCreator instances.
 */
public class PartCreatorsFactory {

    /**
     * Part number.
     */
    private int partNumber;

    /**
     * Output directory.
     */
    private final String outputDirectory;

    /**
     * Progress monitor.
     */
    private final IProgressMonitor progressMonitor;

    /**
     * Creates the PartCreatorsFactory instance specified by part size.
     *
     * @param outputDirectory output directory
     */
    public PartCreatorsFactory(final String outputDirectory, final IProgressMonitor progressMonitor) {
        this.partNumber = 0;
        this.outputDirectory = outputDirectory;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Creates new PartCreator object.
     *
     * @return PartCreator object
     */
    public PartCreator create(final long partSize) {
        return new PartCreator(partNumber++, partSize, outputDirectory, progressMonitor);
    }
}