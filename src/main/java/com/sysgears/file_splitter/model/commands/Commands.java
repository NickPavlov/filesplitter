package com.sysgears.file_splitter.model.commands;

import com.sysgears.file_splitter.model.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Contains the list of all permissible commands.
 */
public enum Commands {
    HELP("help"),
    EXIT("exit"),
    SPLIT("split"),
    UNKNOWN_COMMAND("");

    /**
     * Command name.
     */
    private final String name;

    /**
     * Regex pattern.
     */
    private final String regex;

    /**
     * @param name a command name
     */
    private Commands(final String name) {
        this.name = name;
        this.regex = Converter.buildRegex(name);
    }

    /**
     * Parses the input command and returns the command from the <code>Commands</code>.
     *
     * @param expression input expression
     * @return parsed command
     */
    public static Commands parse(final String expression) {
        List<Commands> commands = new ArrayList<Commands>(Arrays.asList(Commands.values()));
        Iterator<Commands> iterator = commands.iterator();

        return null;
    }

    /**
     * Returns a command name.
     *
     * @return a command name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a regular expression.
     *
     * @return a regex pattern
     */
    public String getRegex() {
        return regex;
    }
}