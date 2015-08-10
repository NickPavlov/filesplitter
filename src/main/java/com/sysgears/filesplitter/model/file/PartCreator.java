package com.sysgears.filesplitter.model.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * The PartCreator class provides functionality to create a part of the file.
 */
public class PartCreator implements IDataProcessor {

    /**
     * The file part number.
     */
    private final int partNumber;

    /**
     * The file part size.
     */
    private final long partSize;

    /**
     * Output directory.
     */
    private final String outputDirectory;

    /**
     * Creates the PartCreator object specified by part name and size.
     *
     * @param partNumber file part number
     * @param partSize final part size
     * @param outputDirectory output directory
     */
    public PartCreator(final int partNumber, final long partSize, final String outputDirectory) {
        this.partNumber = partNumber;
        this.partSize = partSize;
        this.outputDirectory = outputDirectory;
    }

    /**
     * Initiates the process of file part creation.
     *
     * @param originalFile original file
     * @return true if part of the file created successfully, false otherwise
     * @throws IOException in case of data access error
     */
    public boolean process(final IData originalFile) throws IOException {
        //System.out.println(partNumber + " size=" + partSize + " file=" + originalFile.getName());
        ByteBuffer buffer = ByteBuffer.allocate(12);
        FileChannel fileChannel = (FileChannel) originalFile.getChannel();
        fileChannel.read(buffer);
        buffer.flip();
        new FileOutputStream(new File(new File(outputDirectory), partNumber + ".txt")).getChannel().write(buffer);
        System.out.println(partNumber + ": " + new String(buffer.array()));

        return false;
    }
}