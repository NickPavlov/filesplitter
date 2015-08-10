package com.sysgears.filesplitter;

import com.sysgears.filesplitter.view.UserInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            final ExecutorService pool = Executors.newFixedThreadPool(2);
            final UserInterface ui = new UserInterface(System.in, System.out);

            new Service(pool, ui).start();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}