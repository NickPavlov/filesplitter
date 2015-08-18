package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataIterator;
import com.sysgears.filesplitter.model.abstractmodel.Worker;
import com.sysgears.filesplitter.model.consoleoptions.SplitOptions;
import com.sysgears.filesplitter.model.filesystem.file.FileData;
import com.sysgears.filesplitter.model.filesystem.file.FileFinder;
import com.sysgears.filesplitter.model.filesystem.file.filebuilder.FileBuilder;
import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
import com.sysgears.filesplitter.view.IUserInterface;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The FileBuildService class provides functionality to create file form parts.
 */
public class FileBuildService implements Runnable {

    /**
     * Pool of threads.
     */
    private final ExecutorService pool;

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * Split options.
     */
    private final SplitOptions splitOptions;

    /**
     * Progress monitor.
     */
    private final IProgressMonitor progressMonitor;

    /**
     * Creates the FileBuildService instance.
     *
     * @param pool            pool of threads
     * @param ui              user interface
     * @param splitOptions    command line options
     * @param progressMonitor progress info monitor
     */
    public FileBuildService(final ExecutorService pool,
                            final IUserInterface ui,
                            final SplitOptions splitOptions,
                            final IProgressMonitor progressMonitor) {
        this.ui = ui;
        this.pool = pool;
        this.splitOptions = splitOptions;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Starts a service.
     */
    public void run() {
        try {
            String partPath = "/home/nick/Documents/jdk.tar.gz_parts/jdk.tar.gz_part0.bin";

            final String regexPattern = ".+(?=_part[0-9]+\\.bin)";
            final Pattern pattern = Pattern.compile(regexPattern);
            String originalFileName = Long.toString(System.nanoTime());
            Matcher matcher = pattern.matcher(new File(partPath).getName());
            if (matcher.find()) {
                originalFileName = matcher.group();
            }
            System.out.println(originalFileName);



            IDataIterator fileIterator = new FileFinder(partPath).iterator();
            FileData fileData = new FileData(new File("/home/nick/Documents/jdk.tar.gz_parts/restored.tar.gz"));
            IData filePart;


            int i = 0;
            while (fileIterator.hasNext()) {
                filePart = fileIterator.next();

                if (pattern.matcher(filePart.getName()).find()) {
                    pool.execute(new Worker("worker-" + i++, filePart, new FileBuilder(fileData)));
                }

                /*
                System.out.println("File name: " + filePart.getName());
                System.out.println("File size: " + filePart.getSize());
                System.out.println("");
                */

            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
