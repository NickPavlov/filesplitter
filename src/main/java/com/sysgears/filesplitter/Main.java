package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.MainController;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Main class. Configures and starts the application.
 */
public class Main {

    /**
     * Logger.
     */
    private final static Logger LOG = Logger.getLogger(Main.class);

    /**
     * Application context paths.
     */
    public static final String[] contextPaths = new String[] {
            "beans/controller-beans.xml",
            "beans/model-beans.xml",
            "beans/view-beans.xml"};

    /**
     * Starts the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        try {
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);

            context.getBean(MainController.class).start(args);

        } catch (Throwable t) {
            LOG.fatal(t.getMessage());
            t.printStackTrace();
        }
    }
}