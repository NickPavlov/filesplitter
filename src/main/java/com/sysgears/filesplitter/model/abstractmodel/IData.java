package com.sysgears.filesplitter.model.abstractmodel;

import java.net.URI;

/**
 * The IData interface defines the behavior of the data storage object.
 */
public interface IData {

    /**
     * Gets a data name.
     *
     * @return data name
     */
    public String getName();

    /**
     * Gets a uniform resource identifier.
     *
     * @return URI
     */
    public URI getURI();
}