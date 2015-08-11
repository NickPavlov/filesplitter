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
     * File lock.
     */
    private static final Object FILE_LOCK = new Object();

    /**
     * Default buffer size - 4MB.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 1024 * 4;

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

        //Test.
        ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
        FileChannel fileChannel = (FileChannel) originalFile.getChannel();
        fileChannel.position(position);
        FileLock lock = fileChannel.lock(position, DEFAULT_BUFFER_SIZE, false);
        fileChannel.read(buffer);
        System.out.println(partNumber + ": " + buffer.array().length);
        /*
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        lock.release();

        //buffer.position(0);
        buffer.flip();
        new FileOutputStream(new File(outputDirectory, "p_" + partNumber + ".bin")).getChannel().write(buffer);

        return false;
    }
}