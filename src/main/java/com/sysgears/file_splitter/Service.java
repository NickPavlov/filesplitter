package com.sysgears.file_splitter;

import com.sysgears.file_splitter.model.commands.Commands;
import com.sysgears.file_splitter.view.IUserInterface;

import java.io.IOException;

/**
 * The Service class performs management of the main parts of the application.
 */
public class Service {

    /**
     * User interface, used to interact with the user.
     */
    private final IUserInterface ui;

    /**
     * Constructs The <code>Service</code> object.
     *
     * @param ui user interface
     * @throws IllegalArgumentException if <code>ui<code/> is null
     */
    public Service(final IUserInterface ui) {
        if (ui == null) {
            throw new IllegalArgumentException("User interface can't be null.");
        }
        this.ui = ui;
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