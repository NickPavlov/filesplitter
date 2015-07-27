package com.sysgears.file_splitter.model.commands;

/**
 * Contains the list of all permissible commands.
 */
public enum Commands {
    HELP("help"),
    EXIT("exit"),
    UNKNOWNCOMMAND("");

    /**
     * The command name.
     */
    private final String name;

    /**
     * @param commandName a command name
     */
    private Commands(final String commandName) {
        this.name = commandName;
    }

    /**
     * Parses the input command and returns the command type from Commands.
     *
     * @param arg string
     * @return Commands
     */
    public static Commands parse(final String arg) {
        //Commands result = Commands.UNKNOWNCOMMAND;

        return null;
    }

    /**
     * Returns a command name.
     *
     * @return string
     */
    public String getName() {
        return name;
    }
}