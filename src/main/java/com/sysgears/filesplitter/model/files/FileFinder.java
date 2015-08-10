package com.sysgears.filesplitter.model.files;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;

import java.io.File;
import java.net.URI;

/**
 * The FileFinder class provides functionality to find files.
 */
public class FileFinder implements IDataFinder {

    /**
     * Root directory.
     */
    private final File rootDirectory;

    /**
     * Creates FileFinder object specified by root path.
     * @param rootDirectory root directory
     */
    public FileFinder(final File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    /**
     * Creates FileFinder object specified by root path.
     *
     * @param rootPath root path
     */
    public FileFinder(final String rootPath) {
        this.rootDirectory = new File(rootPath);
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
     * @param name file name
     * @return FileData object
     */
    public IData getByName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("File name can't be null.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("File name can't be empty.");
        }

        final File file = (rootDirectory != null) && (rootDirectory.length() != 0)
                ? new File(rootDirectory, name)
                : new File(name);

        return new IData() {
            public String getName() {
                return file.getName();
            }

            public URI getURI() {
                return file.toURI();
            }
        };
    }
}