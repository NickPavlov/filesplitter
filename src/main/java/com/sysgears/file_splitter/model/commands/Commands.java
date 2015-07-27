package com.sysgears.file_splitter.model.commands;

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
     * Parses the input command and returns the command type from Commands.
     *
     * @param arg string
     * @return Commands
     */
    public static Commands parse(final String arg) {
        //Commands result = Commands.UNKNOWN_COMMAND;

        return null;
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