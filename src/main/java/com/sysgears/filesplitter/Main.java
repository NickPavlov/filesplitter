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
            "system-beans.xml",
            "controller-beans.xml",
            "model-beans.xml",
            "view-beans.xml"};

    /**
     * Command line arguments.
     */
    public static String[] args;

    /**
     * Starts the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        //System.out.println(System.getProperty("sun.java.command"));
        try {
            Main.args = args;

            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);

            context.getBean(MainController.class).start();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}