package com.sysgears.file_splitter.model.commands;

import org.kohsuke.args4j.Option;

/**
 *
 */
public class SplitFile {

    @Option(name = "-p", aliases = {"--path"}, required = true, usage = "File path.")
    private String path;

    @Option(name = "-s", aliases = {"--size"}, required = true, usage = "Part size.")
    private int partSize;

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @return
     */
    public int getPartSize() {
        return partSize;
    }
}