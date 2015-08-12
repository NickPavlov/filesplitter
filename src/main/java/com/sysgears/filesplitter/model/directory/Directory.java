package com.sysgears.filesplitter.model.directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Directory class provides functionality to work with directories.
 */
public class Directory {

    /**
     * Directory.
     */
    private final File directory;

    /**
     * Creates the Directory instance specified by directory file.
     * Path can be specified for a particular file, in this case
     * the constructor takes the parent directory of the file
     *
     * @param directory directory path
     * @throws IllegalArgumentException if <code>directory</code> is null
     * @throws IOException              if an I/O error
     */
    public Directory(final String directory) throws IOException {
        if (directory == null) {
            throw new IllegalArgumentException("Directory can't be null.");
        }
        if (directory.isEmpty()) {
            throw new IllegalArgumentException("Directory can't be empty.");
        }

        File tempDirectory = new File(directory);
        if (tempDirectory.exists()) {
            if (!tempDirectory.isDirectory()) {
                tempDirectory = tempDirectory.getParentFile();
            }
        } else if (!tempDirectory.mkdir()) {
            throw new IOException("Error creating directory.");
        }
        this.directory = tempDirectory;
    }

    /**
     * Returns an absolute path with inner directory.
     *
     * @param innerDirectory inner directory name
     * @return an absolute path
     */
    public String getWithInnerDirectory(final String innerDirectory) {
        return "";
    }

    /**
     * Returns a directory.
     *
     * @return directory
     */
    public File getDirectory() {
        return directory;
    }

    /**
     * Returns a directory absolute path.
     *
     * @return absolute path
     */
    public String getAbsolutePath() {
        return directory.getAbsolutePath();
    }

    /**
     * Returns a <code>Path</code> of the directory.
     *
     * @return <code>Path</code>
     */
    public Path getPath() {
        return Paths.get(directory.toURI());
    }

    public static void main(String[] args) {
        try {
            System.out.println(new Directory("/home/nick/Documents").getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}