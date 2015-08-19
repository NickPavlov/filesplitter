package com.sysgears.filesplitter.model.console.commands;

/**
 * The Commands class provides set of commands.
 */
public enum Commands {

    SPLIT("split"),
    BUILD("build"),

    UNKNOWN_COMMAND("");

    /**
     * Command name.
     */
    private final String name;

    /**
     * @param name command name
     */
    Commands(final String name) {
        this.name = name;
    }

    /**
     * Returns a command which corresponds to the <code>expression</code>.
     *
     * @param expression expression, to find an appropriate command
     * @return appropriate command
     */
    public static Commands getCommand(final String expression) {
        Commands result = UNKNOWN_COMMAND;
        for (Commands command : Commands.values()) {
            if (command.name.equals(expression)) {
                result = command;
                break;
            }
        }

        return result;
    }

    /**
     * Returns a command name.
     *
     * @return command name
     */
    public String getName() {
        return name;
    }
}