package com.sysgears.filesplitter.model.abstractmodel;

import java.io.IOException;

/**
 * The IDataProcessor defines the behavior of a data processor object.
 */
public interface IDataProcessor {

    /**
     * Processes the received data.
     *
     * @param data data object
     * @return true if the data processed successfully, false otherwise
     * @throws IOException in case if I/O error occurred
     */
    public boolean process(final IData data) throws IOException;
}