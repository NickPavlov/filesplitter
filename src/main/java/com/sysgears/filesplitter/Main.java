package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.MainController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Main class...
 */
public class Main {

    private static String[] args;

    /**
     * Starts the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Main.args = args;

        try {
            final String[] contextPaths = new String[] {"controller-beans.xml", "model-beans.xml", "view-beans.xml"};
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);

            context.getBean(MainController.class).start(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}