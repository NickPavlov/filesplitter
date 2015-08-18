package com.sysgears.filesplitter.model.filesystem.file.filebuilder;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * The FileBuilder class provides functionality to build file from parts.
 */
public class FileBuilder implements IDataProcessor {

    /**
     * File data.
     */
    private final IData file;

    /**
     * Creates the FileBuilder instance.
     *
     * @param file file to build
     */
    public FileBuilder(final IData file) {
        this.file = file;
    }

    /**
     * Initiates the process of appending a file part into the main file.
     *
     * @param filePart part of a file
     * @return true if part of the file added successfully
     * @throws IOException if an I/O error occurred
     */
    public boolean process(final IData filePart) throws IOException {
        try (
                final FileChannel inputChannel = (FileChannel) filePart.getChannel();
                final FileChannel outputChannel = (FileChannel) file.getChannel();
        ) {

            ByteBuffer buffer = ByteBuffer.allocate(512);
            inputChannel.read(buffer);
            buffer.flip();
            System.out.println(filePart.getName());
            /*
            int size = buffer.getInt();
            System.out.println("size=" + size);
            byte[] bytes = new byte[size];
            buffer.get(bytes);
            System.out.println("name=" + new String(bytes));
            System.out.println(buffer.getLong());
            */
        }

        return false;
    }
}