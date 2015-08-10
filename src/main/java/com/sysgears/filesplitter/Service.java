package com.sysgears.filesplitter;

import com.sysgears.filesplitter.model.WorkerFactory;
import com.sysgears.filesplitter.model.abstractmodel.IDataFinder;
import com.sysgears.filesplitter.model.abstractmodel.IDataProcessor;
import com.sysgears.filesplitter.model.commands.Commands;
import com.sysgears.filesplitter.model.file.FileFinder;
import com.sysgears.filesplitter.model.file.FilePartCreator;
import com.sysgears.filesplitter.view.IUserInterface;

import java.io.IOException;
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
     * @param ui user interface
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
     */
    public void start() {
        //Test.
        IDataFinder fileFinder = new FileFinder("/home/nick/Documents");
        IDataProcessor partCreator = new FilePartCreator("part_0", 1024);
        pool.execute(new WorkerFactory(fileFinder.getByName("test.txt")).create(partCreator));
        pool.shutdown();
    }

    /**
     * Performs an action, depending on the type of received command.
     *
     * @param command command type
     * @param message string
     * @throws IOException in case I/O error
     */
    private void execute(final Commands command, final String message) throws IOException {

    }
}