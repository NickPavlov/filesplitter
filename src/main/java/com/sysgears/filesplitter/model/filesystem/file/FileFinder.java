package com.sysgears.filesplitter.model.filesystem.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.abstractmodel.IDataIterator;
import com.sysgears.filesplitter.model.filesystem.directory.Directory;

import java.io.File;
import java.io.IOException;

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

        return new FileData(file);
    }

    /**
     * Returns <code>FileIterator</code> instance.
     *
     * @return <code>FileIterator</code>
     * @throws IOException
     */
    public IDataIterator iterator() throws IOException {
        return new FileIterator(new Directory(rootDirectory.getAbsolutePath()));
    }
}