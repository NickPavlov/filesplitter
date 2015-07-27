package com.sysgears.file_splitter;

import com.sysgears.file_splitter.view.UserInterface;

/**
 * The <code>Main</code> class...
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args console arguments
     */
    public static void main(final String[] args) {
        new Service(new UserInterface(System.in, System.out)).start();
    }
}