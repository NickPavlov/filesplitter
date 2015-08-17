package com.sysgears.filesplitter.model.filesystem.util;

import java.nio.ByteBuffer;

/**
 * The ByteBufferCreator class provides functionality to create a byte buffer.
 */
public class ByteBufferCreator {

    /**
     * Creates byte sequence from the given string.
     * Appends length of the string at the beginning (4 bytes).
     *
     * @param string string to create byte sequence.
     * @return byte sequence
     */
    public static ByteBuffer createFromString(final String string, final int bytesForLength) {
        byte[] bytes = string.getBytes();
        ByteBuffer result = ByteBuffer.allocate(bytesForLength + bytes.length).putInt(bytes.length).put(bytes);
        result.flip();

        return result;
    }

    private ByteBufferCreator() {
    }
}