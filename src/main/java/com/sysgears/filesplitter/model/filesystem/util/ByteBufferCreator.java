package com.sysgears.filesplitter.model.filesystem.util;

import java.nio.ByteBuffer;

/**
 * The ByteBufferCreator class provides functionality to create a <code>ByteBuffer</code> instances.
 */
public class ByteBufferCreator {

    /**
     * Creates <code>ByteBuffer</code> from a string.
     * Appends length of the string at the beginning.
     *
     * @param string string to create <code>ByteBuffer</code>.
     * @return <code>ByteBuffer</code>
     */
    public static ByteBuffer createFromString(final String string, final int bytesForLength) {
        byte[] bytes = string.getBytes();
        ByteBuffer result = ByteBuffer.allocate(bytesForLength + bytes.length).putInt(bytes.length).put(bytes);
        result.flip();

        return result;
    }

    /**
     * Creates <code>ByteBuffer</code> from a long value.
     *
     * @param longValue long value to create <code>ByteBuffer</code>
     * @return <code>ByteBuffer</code>
     */
    public static ByteBuffer createFromLong(final long longValue) {
        ByteBuffer result = ByteBuffer.allocate(8).putLong(longValue);
        result.flip();

        return result;
    }

    private ByteBufferCreator() {
    }
}