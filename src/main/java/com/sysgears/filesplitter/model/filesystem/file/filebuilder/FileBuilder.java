package com.sysgears.filesplitter.model.filesystem.file.filebuilder;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.IOException;

/**
 * The FileBuilder class provides functionality to build file from parts.
 */
public class FileBuilder implements IDataProcessor {

    /**
     * Initiates the process of appending a file part into the main file.
     *
     * @param filePart part of a file
     * @return true if part of the file added successfully
     * @throws IOException if an I/O error occurred
     */
    public boolean process(final IData filePart) throws IOException {
        return false;
    }
}