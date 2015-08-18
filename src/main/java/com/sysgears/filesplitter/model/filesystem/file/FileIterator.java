package com.sysgears.filesplitter.model.filesystem.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;

import java.io.IOException;

/**
 * The FileIterator class provides functionality to iterate over files.
 */
public class FileIterator {



    public FileIterator(final String rootDirectory) throws IOException {


    }

    /**
     * Returns true if the next object exist, false otherwise.
     *
     * @return true if the next object exist, false otherwise.
     */
    public boolean hasNext() {
        return false;
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