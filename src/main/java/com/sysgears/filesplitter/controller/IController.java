package com.sysgears.filesplitter.controller;

/**
 * The IController interface defines the behavior of a controller object.
 */
public interface IController {

    /**
     * Starts a controller.
     *
     * @param args command line arguments
     */
    public void start(final String[] args);

    /**
     * Stops a controller.
     */
    public void stop();
}