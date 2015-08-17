package com.sysgears.filesplitter.model.util;

import java.nio.ByteBuffer;

/**
 * The BytesCreator class provides functionality to create byte sequences.
 */
public class BytesCreator {


    /**
     * Creates byte sequence from the given string.
     * appends the length of the string at the beginning.
     *
     * @param string string to create byte sequence.
     * @return byte sequence
     */
    public byte[] createFromString(final String string) {
        byte[] bytes = string.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.flip();

        return buffer.array();
    }
}