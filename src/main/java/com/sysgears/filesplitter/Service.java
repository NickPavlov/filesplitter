package com.sysgears.filesplitter;

import com.sysgears.filesplitter.model.WorkerFactory;
import com.sysgears.filesplitter.model.abstractmodel.IData;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.file.FileFinder;
import com.sysgears.filesplitter.model.file.part.PartCreatorFactory;
import com.sysgears.filesplitter.view.IUserInterface;

import java.util.concurrent.ExecutorService;

/**
 * The Service class performs management of the main parts of the application.
 */
public class Service {

    /**
     * Pool of threads.
     */
    private final ExecutorService pool;

    /**
     * User interface.
     */
    private final IUserInterface ui;

    /**
     * Creates the Service object specified by the pool and user interface.
     *
     * @param pool pool of threads
     * @param ui   user interface
     * @throws IllegalArgumentException if user interface is null
     */
    public Service(final ExecutorService pool, final IUserInterface ui) {
        if (pool == null) {
            throw new IllegalArgumentException("Pool of threads can't be null.");
        }
        if (ui == null) {
            throw new IllegalArgumentException("User interface can't be null.");
        }
        this.pool = pool;
        this.ui = ui;
    }

    /**
     * Starts the service.
     *
     * @param args console arguments
     */
    public void start(final String[] args) {
        /*
        SplitOptions splitOptions = new SplitOptions();
        CmdLineParser cmdLineParser = new CmdLineParser(splitOptions);
        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        System.out.println("Path: " + splitOptions.getPath());
        System.out.println("PartSize: " + splitOptions.getPartSize());
        System.out.println("MB: " + splitOptions.isMegabytes());
        System.out.println("kB: " + splitOptions.isKilobytes());
        */

        int partSize = 1024 * 1024 * 50;

        final IDataFinder fileFinder = new FileFinder("/home/nick/Documents");
        final PartCreatorFactory partCreator = new PartCreatorFactory(partSize, "/home/nick/Documents/Parts");
        final IData file = fileFinder.getByName("jdk.tar.gz");
        final WorkerFactory workerFactory = new WorkerFactory(file);

        for (int i = 0; i < file.getSize() / partSize + 1; ++i) {
            pool.execute(workerFactory.create(partCreator.create()));
            System.out.println("thread-"+i);
        }
        pool.shutdown();
    }
}