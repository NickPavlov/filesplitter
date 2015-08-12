package com.sysgears.filesplitter;

import com.sysgears.filesplitter.controller.FileSplitController;
import com.sysgears.filesplitter.view.UserInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Application configuration.
 */
@Configuration
public class AppConfig {

    @Bean
    public FileSplitController fileSplitController(){
        final ExecutorService pool = Executors.newFixedThreadPool(2);
        final UserInterface ui = new UserInterface(System.in, System.out);

        return new FileSplitController(pool, ui);
    }
}