package com.sysgears.file_splitter.model.commands;

import com.sysgears.file_splitter.model.commands.options.SplitOptions;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.ArrayList;
import java.util.List;

public class SplitOptionsTest {

    public static void main(String[] args) {
        try {

            SplitOptions splitOptions = new SplitOptions();

            CmdLineParser cmdLineParser = new CmdLineParser(splitOptions);

            List<String> params = new ArrayList<String>();

            params.add("-p");
            params.add("/home/nick/Documents");
            params.add("-s");
            //params.add("1073741824");
            params.add("512");
            params.add("-mb");

            cmdLineParser.parseArgument(params);

            System.out.println(params.toString());
            System.out.println();
            cmdLineParser.printUsage(System.out);
            System.out.println();
            System.out.println("Path: " + splitOptions.getPath());
            System.out.println("Part size: " + splitOptions.getPartSize());
            System.out.println("Megabytes: " + splitOptions.isMegabytes());
            System.out.println("Kilobytes: " + splitOptions.isKilobytes());


        } catch (CmdLineException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }
}