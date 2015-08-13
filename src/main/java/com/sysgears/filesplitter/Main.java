package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.services.FileSplitService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

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
            final CommandLinePropertySource cmdArgs = new SimpleCommandLinePropertySource(args);
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configFiles);
            context.getEnvironment().getPropertySources().addFirst(cmdArgs);

            final FileSplitService splitService = context.getBean(FileSplitService.class);
            splitService.start(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}