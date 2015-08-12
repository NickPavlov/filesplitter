package com.sysgears.filesplitter.model.directory;

import java.io.File;
import java.io.IOException;

/**
 * The Directory class provides functionality to work with directories.
 */
public class Directory {

    /**
     * Directory.
     */
    private final File directory;

    /**
     * Creates the Directory instance.
     *
     * @param directory directory
     * @throws IllegalArgumentException in case if <code>directory</code> is null
     * @throws IOException
     */
    public Directory(final File directory) throws IOException {
        if (directory == null) {
            throw new IllegalArgumentException("Directory can't be null.");
        }
        if (directory.exists()) {
            this.directory = directory.isDirectory() ? directory : directory.getParentFile();
        } else {
            if (directory.mkdir()) {
                this.directory = directory;
            } else {
                throw new IOException("Error creating directory.");
            }
        }

    }
}