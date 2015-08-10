package com.sysgears.filesplitter.model.file;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The PartCreator class provides functionality to create a part of the file.
 */
public class PartCreator implements IDataProcessor {

    /**
     * The file part name.
     */
    private final String partName;

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
     * @param partName file part name
     * @param partSize final part size
     * @param outputDirectory output directory
     */
    public PartCreator(final String partName, final long partSize, final String outputDirectory) {
        this.partName = partName;
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
        //System.out.println(partName + " size=" + partSize + " file=" + originalFile.getName());
        ByteBuffer buffer = ByteBuffer.allocate(12);
        File file = new File(originalFile.getURI());
        new FileInputStream(file).getChannel().read(buffer);
        String parentFolder = file.getParent();


        return false;
    }

    /**
     * Returns a name of the part of the file.
     *
     * @return file part name
     */
    public String getName() {
        return partName;
    }

    /**
     * Returns part size.
     *
     * @return part size
     */
    public long getPartSize() {
        return partSize;
    }

}