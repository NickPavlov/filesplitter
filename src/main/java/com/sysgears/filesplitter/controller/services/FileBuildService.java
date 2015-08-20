package com.sysgears.filesplitter.controller.services;

import com.sysgears.filesplitter.controller.services.util.IService;
import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataIterator;
import com.sysgears.filesplitter.model.console.options.BuildOptions;
import com.sysgears.filesplitter.model.filesystem.file.FileData;
import com.sysgears.filesplitter.model.filesystem.file.FileFinder;
import com.sysgears.filesplitter.model.filesystem.file.filebuilder.FileBuilder;
import com.sysgears.filesplitter.model.statistics.monitor.IProgressMonitor;
import com.sysgears.filesplitter.model.worker.WorkersFactory;
import com.sysgears.filesplitter.view.IUserInterface;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The FileBuildService class provides functionality to create file form parts.
 */
public class FileBuildService implements IService {

    /**
     * Logger.
     */
    private final static Logger LOG = Logger.getLogger(FileBuildService.class);

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
    private final BuildOptions buildOptions;

    /**
     * Progress monitor.
     */
    private final IProgressMonitor progressMonitor;

    /**
     * Creates the FileBuildService instance.
     *
     * @param pool            pool of threads
     * @param ui              user interface
     * @param buildOptions    command line options
     * @param progressMonitor progress info monitor
     */
    public FileBuildService(final ExecutorService pool,
                            final IUserInterface ui,
                            final BuildOptions buildOptions,
                            final IProgressMonitor progressMonitor) {
        this.ui = ui;
        this.pool = pool;
        this.buildOptions = buildOptions;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Starts a service.
     */
    public void start() {
        try {
            LOG.info("FileBuildService started.");

            // temporarily
            //final String partPath = "/home/nick/Documents/jdk.tar.gz_parts/jdk.tar.gz_part0.bin";
            //final String outputPath = "/home/nick/Documents/jdk.tar.gz_parts/";
            final String regexPattern = ".+(?=_part[0-9]+\\.bin)";
            final String partPath = buildOptions.getPartSource();
            final String outputPath = buildOptions.getOutputDirectory();

            final Pattern pattern = Pattern.compile(regexPattern);
            String originalFileName = Long.toString(System.nanoTime());
            Matcher matcher = pattern.matcher(new File(partPath).getName());
            if (matcher.find()) {
                originalFileName = matcher.group();
            }

            IDataIterator fileIterator = new FileFinder(partPath).iterator();
            FileData fileData = new FileData(new File(outputPath, originalFileName));
            WorkersFactory factory = new WorkersFactory();
            IData filePart;
            while (fileIterator.hasNext()) {
                filePart = fileIterator.next();
                if (pattern.matcher(filePart.getName()).find()) {
                    pool.execute(factory.create(filePart, new FileBuilder(fileData, progressMonitor)));
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stops a service.
     */
    public void stop() {
        LOG.info("FileBuildService stopped.");
        pool.shutdown();
    }

    /**
     * Starts a service. It behaves exactly like method <code>start()</code>.
     */
    public void run() {
        start();
    }
}
