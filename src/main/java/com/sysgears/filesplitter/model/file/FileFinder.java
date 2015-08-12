package com.sysgears.filesplitter.model.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * The FileFinder class provides functionality to find file.
 */
public class FileFinder implements IDataFinder {

    /**
     * Root directory.
     */
    private final File rootDirectory;

    /**
     * Creates FileFinder instance specified by root path.
     *
     * @param rootDirectory root directory
     */
    public FileFinder(final File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    /**
     * Creates the FileFinder object specified by root path.
     *
     * @param rootPath root path
     */
    public FileFinder(final String rootPath) {
        this(new File(rootPath));
    }

    /**
     * Creates FileFinder object specified by empty root path.
     */
    public FileFinder() {
        this("");
    }

    /**
     * Returns a file.
     * In case if root directory is unspecified, filename should contain an absolute path.
     *
     * @param filename filename
     * @return IData object
     * @throws IllegalArgumentException if filename null or empty
     */
    public IData getByName(final String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("File filename can't be null.");
        }
        if (filename.isEmpty()) {
            throw new IllegalArgumentException("File filename can't be empty.");
        }

        final File file = (rootDirectory != null) && (rootDirectory.length() != 0)
                ? new File(rootDirectory, filename)
                : new File(filename);

        return new IData() {
            public String getName() {
                return file.getName();
            }

            public long getSize() {
                return file.length();
            }

            public FileChannel getChannel() throws IOException {
                return new RandomAccessFile(file, "rw").getChannel();
            }
        };
    }
}