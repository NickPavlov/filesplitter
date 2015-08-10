package com.sysgears.filesplitter.model.files;

import com.sysgears.filesplitter.model.abstractmodel.IData;

import java.io.File;
import java.net.URI;

/**
 * The FileData class provides functionality to work with files.
 */
public class FileData implements IData {

    /**
     * File.
     */
    private final File file;

    /**
     * Returns a file name.
     *
     * @return file name
     */
    public String getName() {
        return file.getName();
    }

    /**
     * Returns a file URI.
     *
     * @return file URI
     */
    public URI getURI() {
        return file.toURI();
    }

    /**
     * Creates FileData object specified by file.
     * @param file file
     */
    FileData(final File file) {
        this.file = file;
    }
}