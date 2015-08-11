package com.sysgears.filesplitter.model.file.part;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 1024 * 5;

    /**
     * The file part number.
     */
    private final int partNumber;

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
     * Creates the PartCreator object specified by part name and size.
     *
     * @param partNumber      file part number
     * @param partSize        final part size
     * @param outputDirectory output directory
     */
    public PartCreator(final int partNumber, final long partSize, final String outputDirectory) {
        this.partNumber = partNumber;
        this.partSize = partSize;
        this.position = partNumber * partSize;
        this.outputDirectory = outputDirectory;
    }

    /**
     * Initiates the process of the file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully, false otherwise
     * @throws IOException in case of data access error
     */
    public boolean process(final IData originalFile) throws IOException {
        final FileChannel inputChannel = ((FileChannel) originalFile.getChannel()).position(position);
        final String partFileName = "part_" + partNumber + ".bin";
        final FileChannel outputChannel = new FileOutputStream(new File(outputDirectory, partFileName)).getChannel();

        int fullPartsCount;
        int remainingBytes;
        int bufferSize;
        if (partSize < DEFAULT_BUFFER_SIZE) {
            fullPartsCount = 1;
            remainingBytes = 0;
            bufferSize = (int) partSize;
        } else {
            fullPartsCount = (int) (partSize / DEFAULT_BUFFER_SIZE);
            remainingBytes = (int) (partSize - fullPartsCount * DEFAULT_BUFFER_SIZE);
            bufferSize = DEFAULT_BUFFER_SIZE;
        }

        final FileLock lock = inputChannel.lock(position, bufferSize, false);
        ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);

        for (int i = 0; i < fullPartsCount; ++i) {
            buffer.clear();
            inputChannel.read(buffer);
            buffer.flip();
            outputChannel.write(buffer);

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

        return false;
    }
}