package com.sysgears.filesplitter.controller;

/**
 * The MainController class is the main controller that controls the application.
 */
public class MainController implements IController {

    /**
     * FileSplitService instance.
     */
    private final Runnable fileSplitService;

    /**
     * FileBuildService instance.
     */
    private final Runnable fileBuildService;

    /**
     * Creates the MainController instance specified by FileSplitService and FileBuildService.
     *
     * @param fileSplitService FileSplitService
     * @param fileCreateService FileCreateService
     */
    public MainController(final Runnable fileSplitService, final Runnable fileCreateService) {
        this.fileSplitService = fileSplitService;
        this.fileBuildService = fileCreateService;
    }

    /**
     * Starts the controller.
     */
    public void start() {
        fileSplitService.run();
    }

    /**
     * Stops the controller.
     */
    public void stop() {

    }
}