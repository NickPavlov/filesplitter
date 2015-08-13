package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.MainController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Main class...
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        try {
            final String[] configFiles = new String[] {"controller-components.xml", "view-components.xml"};
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configFiles);

            context.getBean(MainController.class).start(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}