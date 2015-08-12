package com.sysgears.filesplitter.model.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * The Resource class provides functionality to work with resources.
 */
public class Resource {

    /**
     * Closes a resource.
     *
     * @param closeable resource
     */
    public static void closeQuietly(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }

    private Resource() {
    }
}