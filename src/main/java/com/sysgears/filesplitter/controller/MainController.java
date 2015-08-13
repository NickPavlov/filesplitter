package com.sysgears.filesplitter.controller;

import com.sysgears.filesplitter.controller.services.IService;

/**
 * The MainController class is the main controller that controls the application.
 */
public class MainController {

    /**
     * FileSplitService.
     */
    private final IService fileSplitService;

    /**
     * FileBuildService.
     */
    private final IService fileBuildService;

    /**
     * Creates the MainController instance specified by FileSplitService and FileBuildService.
     *
     * @param fileSplitService FileSplitService
     * @param fileCreateService FileCreateService
     */
    public MainController(final IService fileSplitService, final IService fileCreateService) {
        this.fileSplitService = fileSplitService;
        this.fileBuildService = fileCreateService;
    }

    /**
     * Starts the controller.
     *
     * @param args command line arguments
     */
    public void start(final String[] args) {
        fileSplitService.start(args);
    }
}