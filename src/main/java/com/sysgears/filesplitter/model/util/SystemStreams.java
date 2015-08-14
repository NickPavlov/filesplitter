package com.sysgears.filesplitter.model.util;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The SystemStreams is a util class. It provides access to system default streams.
 */
public class SystemStreams {

    /**
     * Returns a system input stream.
     *
     * @return default input stream
     */
    public static InputStream getInputStream() {
        return System.in;
    }

    /**
     * Returns a system output stream.
     *
     * @return default output stream
     */
    public static OutputStream getOutputStream() {
        return System.out;
    }
}