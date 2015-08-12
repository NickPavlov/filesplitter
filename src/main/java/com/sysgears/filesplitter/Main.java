package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.SplitController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The Main class creates the necessary objects for calculator work and passes control to the service.
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args console arguments
     */
    public static void main(final String[] args) {
        try {
            final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            final SplitController splitController = (SplitController) context.getBean("splitController");

            splitController.start(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}