package com.sysgears.filesplitter.model.file.partcreator;

import com.sysgears.filesplitter.model.util.MemoryUnits;
import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * The PartCreator class provides functionality to create a part of the file.
 */
public class PartCreator implements IDataProcessor {

    /**
     * Default buffer size - 4MB.
     */
    private static final int DEFAULT_BUFFER_SIZE = 4 * MemoryUnits.MEGABYTE;

    /**
     * The file part size.
     */
    private final long partSize;

    /**
     * Position in file.
     */
    private final long position;

    /**
     * Output directory.
     */
    private final String outputDirectory;

    /**
     * Output file name.
     */
    private String outputFileNameSuffix;

    /**
     * Creates the PartCreator object specified by part name and size.
     *
     * @param partNumber      file part number
     * @param partSize        part size
     * @param outputDirectory output directory
     */
    public PartCreator(final int partNumber, final long partSize, final String outputDirectory) {
        this.partSize = partSize;
        this.position = partNumber * partSize;

        //temporary
        this.outputFileNameSuffix = "_part" + partNumber + ".bin";
        this.outputDirectory = outputDirectory;
    }

    /**
     * Initiates the process of the file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully, false otherwise
     */
    public boolean process(final IData originalFile) throws IOException {
        boolean success = true;
        RandomAccessFile outputFile = new RandomAccessFile(new File(outputDirectory, originalFile.getName()
                + outputFileNameSuffix), "rw");

        FileChannel inputChannel = ((FileChannel) originalFile.getChannel()).position(position);
        FileChannel outputChannel = outputFile.getChannel();

        int bufferSize;
        int fullPartsCount;
        int remainingBytes;
        if (partSize < DEFAULT_BUFFER_SIZE) {
            fullPartsCount = 1;
            remainingBytes = 0;
            bufferSize = (int) partSize;
        } else {
            fullPartsCount = (int) (partSize / DEFAULT_BUFFER_SIZE);
            remainingBytes = (int) (partSize - fullPartsCount * DEFAULT_BUFFER_SIZE);
            bufferSize = DEFAULT_BUFFER_SIZE;
        }

        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        final FileLock lock = inputChannel.lock(position, bufferSize, false);
        for (int partNumber = 0; partNumber < fullPartsCount; ++partNumber) {
            buffer.clear();
            inputChannel.read(buffer);
            buffer.flip();
            outputChannel.write(buffer);

            // ?
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (remainingBytes > 0) {
            buffer = ByteBuffer.allocate(remainingBytes);
            inputChannel.read(buffer);
            buffer.flip();
            outputChannel.write(buffer);
        }
        lock.release();

        closeQuietly(inputChannel);
        closeQuietly(outputChannel);
        closeQuietly(outputFile);

        return success;
    }

    /**
     * Closes a resource.
     *
     * @param closeable resource
     */
    private void closeQuietly(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
}