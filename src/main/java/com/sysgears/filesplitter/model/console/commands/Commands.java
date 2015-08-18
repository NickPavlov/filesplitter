package com.sysgears.filesplitter.model.console.commands;

/**
 *
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
     * Returns a command which corresponds to the expression.
     *
     * @param expression expression, to find an appropriate command
     * @return appropriate command
     */
    public Commands getCommand(final String expression) {
        return null;
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