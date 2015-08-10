package com.sysgears.filesplitter.model.files;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;

/**
 * The FilePart class provides functionality to create a part of the file.
 */
public class FilePart implements IDataProcessor {

    /**
     * Returns a name of the part of the file.
     *
     * @return file part name
     */
    public String getName() {
        return "";
    }

    public boolean process(final IData originalFile) {
        return false;
    }
}