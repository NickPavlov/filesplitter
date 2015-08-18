package com.sysgears.filesplitter.model.filesystem.file.partcreator;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.filesystem.util.ByteBufferCreator;
import com.sysgears.filesplitter.model.filesystem.util.Bytes;
import com.sysgears.filesplitter.model.filesystem.util.MemoryUnits;
import com.sysgears.filesplitter.model.math.partiterator.IPartIterator;
import com.sysgears.filesplitter.model.math.partiterator.PartIterator;
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
     * Placed at the beginning of the file number of the position (long value, 8 bytes) in the original file.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully
     * @throws IOException if an I/O error occurred
     */
    public boolean process(final IData originalFile) throws IOException {
        try (
                final RandomAccessFile outputFile = new RandomAccessFile(
                        new File(outputDirectory, originalFile.getName() + outputFileNameSuffix), "rw");
                final FileChannel inputChannel = ((FileChannel) originalFile.getChannel()).position(position);
                final FileChannel outputChannel = outputFile.getChannel();
        ) {
            outputChannel.write(ByteBufferCreator.createFromLong(position));
            IPartIterator partIterator = new PartIterator(partSize, DEFAULT_BUFFER_SIZE);
            ByteBuffer buffer;
            long readBytes = 0;
            final FileLock lock = inputChannel.lock(position, partSize, false);
            while (partIterator.hasNext()) {
                buffer = ByteBuffer.allocate((int) partIterator.next());
                readBytes += Bytes.transfer(inputChannel, outputChannel, buffer);
                progressMonitor.update(partName, readBytes);
            }
            lock.release();
        }

        return true;
    }
}