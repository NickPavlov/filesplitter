package com.sysgears.filesplitter.model.consoleoptions;

import org.kohsuke.args4j.Option;

/**
 * The SplitOptions class provides functionality to parse a command line parameters.
 */
public class SplitOptions {

    /**
     * File path.
     */
    @Option(name = "-p", aliases = "--filePath", metaVar = "<file>", required = true, usage = "File path.")
    private String filePath;

    /**
     * Output directory path.
     */
    @Option(name = "-o", aliases = "--output", metaVar = "<directory>", required = false, usage = "Output directory.")
    private String outputDirectory;

    /**
     * Part size.
     */
    @Option(name = "-s", aliases = "--size", metaVar = "<bytes>", required = true, usage = "Part size.")
    private int partSize;

    /**
     * Megabytes flag.
     */
    @Option(name = "-mb", forbids = {"-kb"}, aliases = "--megabytes", required = false,
            usage = "Sets units to megabytes.")
    private boolean isMegabytes;

    /**
     * Kilobytes flag.
     */
    @Option(name = "-kb", forbids = {"-mb"}, aliases = "--kilobytes", required = false,
            usage = "Sets units to kilobytes.")
    private boolean isKilobytes;

    /**
     * Returns file path.
     *
     * @return file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns an output directory path.
     *
     * @return output directory path
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Returns part size.
     *
     * @return part size
     */
    public int getPartSize() {
        return partSize;
    }

    /**
     * Returns true if units are megabytes.
     *
     * @return rue if units are megabytes
     */
    public boolean isMegabytes() {
        return isMegabytes;
    }

    /**
     * Returns true if units are kilobytes.
     *
     * @return true if units are kilobytes
     */
    public boolean isKilobytes() {
        return isKilobytes;
    }
}