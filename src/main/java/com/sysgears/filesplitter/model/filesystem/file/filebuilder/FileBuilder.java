package com.sysgears.filesplitter.model.filesystem.file.filebuilder;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.filesystem.util.Bytes;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.math.partiterator.IPartIterator;
import com.sysgears.filesplitter.model.math.partiterator.PartIterator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * The FileBuilder class provides functionality to build file from parts.
 */
public class FileBuilder implements IDataProcessor {

    /**
     * Default buffer size - 4MB.
     */
    private static final int DEFAULT_BUFFER_SIZE = 4 * MemoryUnits.MEGABYTE;

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
            byte longValueSize = 8;
            ByteBuffer buffer = ByteBuffer.allocate(longValueSize);
            inputChannel.read(buffer);
            buffer.flip();

            long position = buffer.getLong();
            long partSize = filePart.getSize() - longValueSize;

            System.out.println(filePart.getName() + " position=" + position + " size=" + partSize);

            IPartIterator partIterator = new PartIterator(filePart.getSize(), DEFAULT_BUFFER_SIZE);
            outputChannel.position(position);
            long readBytes = 0;
            final FileLock lock = outputChannel.lock(position, partSize, false);
            while (partIterator.hasNext()) {
                buffer = ByteBuffer.allocate((int) partIterator.next());
                readBytes += Bytes.transfer(inputChannel, outputChannel, buffer);
            }
            lock.release();
        }

        return true;
    }
}