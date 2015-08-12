package com.sysgears.filesplitter.model.consoleoptions;

import org.kohsuke.args4j.Option;

/**
 * The BuildOptions class provides set of command line options to build a file.
 */
public class BuildOptions {

    /**
     * Source directory.
     */
    @Option(name = "-s", aliases = "--source", metaVar = "<directory>", required = true,
            usage = "Source directory.")
    private String partsSource;

    /**
     * Returns a source directory.
     *
     * @return source directory
     */
    public String getPartsSource() {
        return partsSource;
    }
}