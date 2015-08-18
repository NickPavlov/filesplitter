package com.sysgears.filesplitter.model.filesystem.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.filesystem.directory.Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * The FileIterator class provides functionality to iterate over files.
 */
public class FileIterator {

    /**
     * Path iterator.
     */
    private final Iterator<Path> fileIterator;

    /**
     * Creates FileIterator instance specified by root directory.
     *
     * @param rootDirectory root directory
     * @throws IOException if an I/O error occurred
     */
    public FileIterator(final Directory rootDirectory) throws IOException {
        fileIterator = Files.newDirectoryStream(rootDirectory.getPath()).iterator();

    }

    /**
     * Returns true if the next object exist, false otherwise.
     *
     * @return true if the next object exist, false otherwise.
     */
    public boolean hasNext() {
        return fileIterator.hasNext();
    }

    /**
     * Returns the next data object.
     *
     * @return data object
     */
    public IData next() {
        return null;
    }
}