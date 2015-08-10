package com.sysgears.filesplitter.model.abstractmodel;

import java.net.URI;

/**
 * The IData interface defines the behavior of a data object.
 */
public interface IData {

    /**
     * Gets a uniform resource identifier.
     *
     * @return URI
     */
    public URI getURI();
}