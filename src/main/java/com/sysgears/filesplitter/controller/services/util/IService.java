package com.sysgears.filesplitter.controller.services.util;

/**
 * The IService interface defines the behavior of a service object.
 */
public interface IService extends Runnable {

    /**
     * Starts a service.
     */
    public void start();

    /**
     * Stops a service.
     */
    public void stop();
}