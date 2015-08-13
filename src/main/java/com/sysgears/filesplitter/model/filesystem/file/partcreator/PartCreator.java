package com.sysgears.filesplitter.model.filesystem.file.partcreator;

import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.statistics.ProgressMonitor;
import com.sysgears.filesplitter.model.statistics.ProgressState;
import com.sysgears.filesplitter.model.util.Resource;

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
     * Part name.
     */
    private final String partName;

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
    private final String outputFileNameSuffix;

    /**
     * Progress monitor.
     */
    private final ProgressMonitor progressMonitor;

    /**
     * Creates the PartCreator instance specified by part name and size.
     *
     * @param partNumber      file part number
     * @param partSize        part size
     * @param outputDirectory output directory
     */
    public PartCreator(final int partNumber,
                       final long partSize,
                       final String outputDirectory,
                       final ProgressMonitor progressMonitor) {
        this.partName = "part" + partNumber;
        this.partSize = partSize;
        this.position = partNumber * partSize;
        //temporary
        this.outputFileNameSuffix = "_" + partName + ".bin";
        this.outputDirectory = outputDirectory;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Initiates the process of the file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully, false otherwise
     * @throws IOException in case if I/O error occurred
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

        long readBytes = 0;

        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        final FileLock lock = inputChannel.lock(position, bufferSize, false);
        for (int partNumber = 0; partNumber < fullPartsCount; ++partNumber) {
            buffer.clear();
            readBytes += transferBytes(inputChannel, outputChannel, buffer);
            progressMonitor.update(partName, new ProgressState(readBytes, partSize));

            // ?
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (remainingBytes > 0) {
            buffer = ByteBuffer.allocate(remainingBytes);
            readBytes += transferBytes(inputChannel, outputChannel, buffer);
            progressMonitor.update(partName, new ProgressState(readBytes, partSize));
        }
        lock.release();

        Resource.closeQuietly(inputChannel);
        Resource.closeQuietly(outputChannel);
        Resource.closeQuietly(outputFile);

        return success;
    }

    /**
     * Transfers bytes from the input file channel into the output file channel.
     *
     * @param input input file channel
     * @param output output file channel
     * @param buffer byte buffer
     * @return number of transferred bytes
     *
     * @throws IOException in case if I/O error occurred
     */
    private int transferBytes(final FileChannel input,
                              final FileChannel output,
                              final ByteBuffer buffer) throws IOException {
        input.read(buffer);
        buffer.flip();
        int readBytes = buffer.capacity();
        output.write(buffer);

        return readBytes;
    }
}