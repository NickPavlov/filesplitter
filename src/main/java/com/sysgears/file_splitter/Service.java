package com.sysgears.file_splitter;

import com.sysgears.file_splitter.model.commands.Commands;
import com.sysgears.file_splitter.view.IUserInterface;

import java.io.IOException;

/**
 * The Service class performs management of the main parts of the application.
 */
public class Service {

    /**
     * Default width of the text separator in the terminal.
     */
    public static final int TERMINAL_SEPARATOR_WIDTH = 80;

    /**
     * User interface, used to interact with the user.
     */
    private final IUserInterface ui;

    /**
     * Width of the text separator in the terminal.
     */
    private final int separatorWidth;

    /**
     * Constructs The <code>Service</code> object.
     *
     * @param ui             user interface
     * @param separatorWidth width of the text separator
     * @throws IllegalArgumentException if <code>ui<code/> is null
     */
    public Service(final IUserInterface ui, final int separatorWidth) {
        if (ui == null) {
            throw new IllegalArgumentException("User interface can't be null.");
        }
        this.ui = ui;
        this.separatorWidth = separatorWidth;
    }

    /**
     * Constructs The <code>Service</code> object.
     *
     * @param ui user interface
     */
    public Service(final IUserInterface ui) {
        this(ui, TERMINAL_SEPARATOR_WIDTH);
    }

    /**
     * Starts the service.
     */
    public void start() {

    }

    /**
     * Performs an action, depending on the type of received command.
     *
     * @param command command type
     * @param message string
     * @throws IOException when Input/Output error
     */
    private void execute(final Commands command, final String message) throws IOException {

    }
}