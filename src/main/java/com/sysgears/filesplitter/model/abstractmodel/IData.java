package com.sysgears.filesplitter.model.abstractmodel;

import java.net.URI;

/**
 * The IData interface defines the behavior of the data storage object.
 */
public interface IData {

    /**
     * Returns a data name.
     *
     * @return data name
     */
    public String getName();

    /**
     * Returns a uniform resource identifier.
     *
     * @return URI
     */
    public URI getURI();
}