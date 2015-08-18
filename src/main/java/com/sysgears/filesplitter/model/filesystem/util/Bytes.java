package com.sysgears.filesplitter.model.filesystem.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * The Bytes class provides functionality to work with bytes.
 */
public class Bytes {

    /**
     * Transfers bytes from <code>src</code> to <code>dst</code>.
     * The amount of bytes specified by <code>buffer</code> size.
     *
     * @param src    source channel
     * @param dst    destination channel
     * @param buffer byte buffer
     * @return number of bytes actually transferred
     */
    public static int transfer(final ReadableByteChannel src,
                               final WritableByteChannel dst,
                               final ByteBuffer buffer) throws IOException {

        src.read(buffer);
        buffer.flip();
        dst.write(buffer);

        return buffer.limit();
    }

    private Bytes() {
    }
}