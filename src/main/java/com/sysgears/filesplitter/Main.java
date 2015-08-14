package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.MainController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Main class. Configures and starts the application.
 */
public class Main {

    /**
     * Application context paths.
     */
    public static final String[] contextPaths = new String[] {
            "controller-beans.xml",
            "model-beans.xml",
            "view-beans.xml"};

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
            t.printStackTrace();
        }
    }
}