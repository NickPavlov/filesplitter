package com.sysgears.filesplitter.model.consoleoptions;

import org.kohsuke.args4j.Option;

/**
 * The BuildOptions class provides set of command line options to build a file.
 */
public class BuildOptions {

    /**
     * File part.
     */
    @Option(name = "-p", aliases = "--part", metaVar = "<file>", required = true,
            usage = "Source directory.")
    private String partPath;

    /**
     * Output directory.
     */
    @Option(name = "-o", aliases = "--output", metaVar = "<directory>", required = true,
            usage = "Source directory.")
    private String outputDirectory;

    /**
     * Returns a part path.
     *
     * @return part path
     */
    public String getPartsSource() {
        return partPath;
    }

    /**
     * Returns an output directory path.
     *
     * @return output directory path
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }
}