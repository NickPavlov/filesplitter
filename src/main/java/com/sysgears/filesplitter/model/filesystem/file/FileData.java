package com.sysgears.filesplitter.model.filesystem.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * The FileData class provides functionality to work with file data.
 */
public class FileData implements IData {

    /**
     * File.
     */
    private final File file;

    /**
     * Creates the FileData instance.
     *
     * @param file file
     */
    public FileData(final File file) {
        this.file = file;
    }

    /**
     * Returns a file name.
     *
     * @return file name
     */
    public String getName() {
        return file.getName();
    }

    /**
     * Returns file size.
     *
     * @return file size
     */
    public long getSize() {
        return file.length();
    }

    /**
     * Returns a file channel.
     *
     * @return file channel
     * @throws IOException if an I/O error occurred
     */
    public FileChannel getChannel() throws IOException {
        return new RandomAccessFile(file, "rw").getChannel();
    }
}