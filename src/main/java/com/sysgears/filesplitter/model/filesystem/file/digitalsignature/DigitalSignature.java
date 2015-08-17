package com.sysgears.filesplitter.model.filesystem.file.digitalsignature;

/**
 * The DigitalSignature class provides functionality to create digital signature.
 */
public class DigitalSignature {

    /**
     * Signature text.
     */
    private final String text;

    /**
     * Signature bytes.
     */
    private final byte[] bytes;

    /**
     * Returns a signature text.
     *
     * @return signature text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns signature bytes.
     *
     * @return signature bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Creates the DigitalSignature instance specified by a text and value.
     *
     * @param text  signature text
     * @param bytes signature bytes
     */
    DigitalSignature(final String text, final byte[] bytes) {
        this.text = text;
        this.bytes = null;
    }
}