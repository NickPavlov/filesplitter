package com.sysgears.filesplitter.model.filesystem.file.digitalsignature;

import java.nio.ByteBuffer;

/**
 * The DigitalSignatureCreator class provides functionality to create digital signature.
 */
public class DigitalSignatureCreator {

    /**
     * Creates digital signature from the given text.
     *
     * @param text text to create digital signature from
     *
     * @return DigitalSignature instance
     */
    public DigitalSignature create(final String text) {
        byte[] bytes = text.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(buffer);
        buffer.flip();

        return new DigitalSignature(text, buffer.array());
    }
}