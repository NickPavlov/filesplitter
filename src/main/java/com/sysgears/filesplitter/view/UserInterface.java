package com.sysgears.filesplitter.view;

import java.io.*;

/**
 * The <code>UserInterface</code> class provides methods for interacting with a user.
 */
public class UserInterface implements IUserInterface {

    /**
     * Default charset name.
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Input stream, used to send data to the user.
     */
    private final InputStream inputStream;

    /**
     * Output stream, used to receive data from user.
     */
    private final OutputStream outputStream;

    /**
     * Charset name.
     */
    private String charsetName;

    /**
     * Creates the UserInterface object with the specified input, output streams and charset name.
     *
     * @param input  input stream
     * @param output output stream
     * @throws IllegalArgumentException if input or output stream is null
     */
    public UserInterface(final InputStream input, final OutputStream output, final String charsetName) {
        this.inputStream = input;
        this.outputStream = output;
        this.charsetName = (charsetName == null || charsetName.isEmpty()) ? DEFAULT_CHARSET : charsetName;
    }

    /**
     * Creates the UserInterface object with the specified
     * input, output streams and <code>DEFAULT_CHARSET</code> charset.
     *
     * @param input  input stream
     * @param output output stream
     */
    public UserInterface(final InputStream input, final OutputStream output) {
        this(input, output, DEFAULT_CHARSET);
    }

    /**
     * Sends a message to the user.
     *
     * @param message a message to be displayed
     * @throws IOException in case if I/O error occurred
     */
    public void sendMessage(final String message) throws IOException {
        if (message == null) {
            throw new IllegalArgumentException("Message can't be null.");
        }
        outputStream.write(message.getBytes(charsetName));
    }

    /**
     * Receives a message from user.
     *
     * @return message from user
     * @throws IOException in case if I/O error occurred
     */
    public String read() throws IOException {
        return new BufferedReader(new InputStreamReader(inputStream, charsetName)).readLine();
    }

    /**
     * Returns the input stream.
     *
     * @return input stream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Returns the output stream.
     *
     * @return output stream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Returns a charset name.
     *
     * @return charset name
     */
    public String getCharsetName() {
        return charsetName;
    }
}