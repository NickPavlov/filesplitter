package com.sysgears.file_splitter.model.commands;

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
     * The command name.
     */
    private final String name;

    /**
     * @param name a command name
     */
    private Commands(final String name) {
        this.name = name;
    }

    /**
     * Parses the input command and returns the command from the <code>Commands</code>.
     *
     * @param expression input expression
     * @return parsed command
     */
    public static Commands parse(final String expression) {
        //Commands result = Commands.UNKNOWN_COMMAND;
        List<Commands> commands = new ArrayList<Commands>(Arrays.asList(Commands.values()));
        Iterator<Commands> iterator = commands.iterator();
        Commands command = iterator.hasNext() ? iterator.next() : Commands.UNKNOWN_COMMAND;
        while (iterator.hasNext()) {
        }

        return command;
    }

    /**
     * Returns a command name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }
}