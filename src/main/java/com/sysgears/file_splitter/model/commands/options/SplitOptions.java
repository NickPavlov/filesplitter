package com.sysgears.file_splitter.model.commands.options;

import org.kohsuke.args4j.Option;

/**
 * The <code>SplitOptions</code> class provides functionality to parse a command line parameters.
 */
public class SplitOptions {

    /**
     * File path.
     */
    @Option(name = "-p", aliases = "--path", metaVar = "<file>", required = true, usage = "File path.")
    private String path;

    /**
     * Part size.
     */
    @Option(name = "-s", aliases = "--size", metaVar = "<bytes>", required = true, usage = "Part size.")
    private int partSize;

    /**
     * Megabytes flag.
     */
    @Option(name = "-mb", forbids={"-kb"}, aliases = "--megabytes", required = false,
            usage = "Sets units to megabytes.")
    private boolean isMegaBytes;

    /**
     * Kilobytes flag.
     */
    @Option(name = "-kb", forbids={"-mb"}, aliases = "--kilobytes", required = false,
            usage = "Sets units to kilobytes.")
    private boolean isKiloBytes;

    /**
     * Returns file path.
     *
     * @return file path
     */
    public String getPath() {
        return path;
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
     * @return boolean
     */
    public boolean isMegaBytes() {
        return isMegaBytes;
    }
}