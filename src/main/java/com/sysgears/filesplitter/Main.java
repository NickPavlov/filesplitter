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
            final String[] contextPaths = new String[] {"controller-beans.xml", "model-beans.xml", "view-beans.xml"};
            //final Map<String, Object> map = new HashMap<String, Object>();
            //map.put("args", args);
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);

            context.getBean(MainController.class).start(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}