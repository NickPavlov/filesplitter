package com.sysgears.filesplitter.controller.services;

/**
 * The IService interface defines the behavior of a controller object.
 */
public interface IService {

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