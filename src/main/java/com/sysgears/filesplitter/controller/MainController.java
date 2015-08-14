package com.sysgears.filesplitter.controller;

import com.sysgears.filesplitter.controller.services.FileBuildService;
import com.sysgears.filesplitter.controller.services.FileSplitService;

/**
 * The MainController class is the main controller that controls the application.
 */
public class MainController implements IController {

    /**
     * FileSplitService instance.
     */
    private final FileSplitService fileSplitService;

    /**
     * FileBuildService instance.
     */
    private final FileBuildService fileBuildService;

    /**
     * Creates the MainController instance specified by FileSplitService and FileBuildService.
     *
     * @param fileSplitService FileSplitService
     * @param fileCreateService FileCreateService
     */
    public MainController(final FileSplitService fileSplitService, final FileBuildService fileCreateService) {
        this.fileSplitService = fileSplitService;
        this.fileBuildService = fileCreateService;
    }

    /**
     * Starts the controller.
     *
     * @param args command line arguments
     */
    public void start(final String[] args) {
        fileSplitService.setArgs(args);
        fileSplitService.run();
    }

    /**
     * Stops the controller.
     */
    public void stop() {

    }
}