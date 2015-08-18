package com.sysgears.filesplitter.model.filesystem.file.partcreator;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.filesystem.util.ByteBufferCreator;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.partiterator.IPartIterator;
import com.sysgears.filesplitter.model.partiterator.PartIterator;
import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * The PartCreator class provides functionality to create a part of a file.
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
    private final IProgressMonitor progressMonitor;

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
                       final IProgressMonitor progressMonitor) {

        this.partName = "part" + partNumber;
        this.partSize = partSize;
        this.position = partNumber * partSize;
        //temporary
        this.outputFileNameSuffix = "_" + partName + ".bin";
        this.outputDirectory = outputDirectory;
        this.progressMonitor = progressMonitor;
        progressMonitor.register(partName, partSize);
    }

    /**
     * Initiates the process of the file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully
     */
    public boolean process(final IData originalFile) throws IOException {
        try (
                final RandomAccessFile outputFile = new RandomAccessFile(
                        new File(outputDirectory, originalFile.getName() + outputFileNameSuffix), "rw");
                final FileChannel inputChannel = ((FileChannel) originalFile.getChannel()).position(position);
                final FileChannel outputChannel = outputFile.getChannel();
        ) {
            outputChannel.write(ByteBufferCreator.createFromString(originalFile.getName(), 4));
            outputChannel.write(ByteBufferCreator.createFromLong(position));

            IPartIterator partIterator = new PartIterator(partSize, DEFAULT_BUFFER_SIZE);

            ByteBuffer buffer;
            long readBytes = 0;
            final FileLock lock = inputChannel.lock(position, partSize, false);
            while (partIterator.hasNext()) {
                buffer = ByteBuffer.allocate(partIterator.next().intValue());
                inputChannel.read(buffer);
                buffer.flip();
                readBytes += buffer.capacity();
                outputChannel.write(buffer);
                progressMonitor.update(partName, readBytes);
            }
            lock.release();
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel =
                new RandomAccessFile("/home/nick/Documents/jdk.tar.gz_parts/jdk.tar.gz_part1.bin", "rw").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        fileChannel.read(buffer);
        buffer.flip();


        int size = buffer.getInt();
        System.out.println("size=" + size);
        byte[] bytes = new byte[size];
        buffer.get(bytes);
        System.out.println("name=" + new String(bytes));
        System.out.println(buffer.getLong());
    }
}