package com.sysgears.filesplitter.model.abstractmodel;

import java.io.IOException;

/**
 * The IDataProcessor defines the behavior of a data processor object.
 */
public interface IDataProcessor {

    /**
     * Returns a processor name.
     *
     * @return processor name
     */
    public String getName();

    /**
     * Processes the received data.
     *
     * @param data data object
     * @return true if the data processed successfully, false otherwise
     * @throws IOException in case of data access error
     */
    public boolean process(final IData data) throws IOException;
}