package com.sysgears.file_splitter;

import com.sysgears.file_splitter.view.UserInterface;

/**
 * The Main class creates the necessary objects for calculator work and passes control to the service.
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args console arguments
     */
    public static void main(final String[] args) {
        try {
            new Service(new UserInterface(System.in, System.out)).start();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}