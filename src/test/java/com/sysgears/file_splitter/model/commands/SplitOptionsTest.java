package com.sysgears.file_splitter.model.commands;

import com.sysgears.file_splitter.model.commands.options.SplitOptions;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.ArrayList;
import java.util.List;

public class SplitOptionsTest {

    public static void main(String[] args) {
        try {

            SplitOptions clOptions = new SplitOptions();

            CmdLineParser cmdLineParser = new CmdLineParser(clOptions);

            List<String> params = new ArrayList<String>();

            params.add("-p");
            params.add("/home/nick/Documents");
            params.add("-s");
            //params.add("1073741824");
            params.add("512");
            params.add("-kb");

            cmdLineParser.parseArgument(params);

            System.out.println(params.toString());
            System.out.println();
            cmdLineParser.printUsage(System.out);
            System.out.println();
            System.out.println("Path: " + clOptions.getPath());
            System.out.println("Part size: " + clOptions.getPartSize());
            System.out.println("Megabytes: " + clOptions.isMegaBytes());
            System.out.println("Megabytes: " + clOptions.isKiloBytes());


        } catch (CmdLineException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }
}