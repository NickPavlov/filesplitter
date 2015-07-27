package com.sysgears.file_splitter.model.commands;

import com.sysgears.file_splitter.model.converter.Converter;

import java.util.ArrayList;

/**
 * Contains the list of all permissible commands.
 */
public enum Command {
    HELP("help", "Display the list of all available commands."),
    EXIT("exit", "Exit the program."),
    UNKNOWNCOMMAND("", "");

    /**
     * The command name.
     */
    private final String commandName;

    /**
     * The command description.
     */
    private final String description;

    /**
     * Regex filter to search for commands in a string.
     */
    private final String regex;

    /**
     * @param commandName a command name
     * @param description description of the command
     */
    private Command(final String commandName, final String description) {
        this.commandName = commandName;
        this.description = description;
        this.regex = (description.equals("")) ? commandName : Converter.buildRegex(commandName);
    }

    /**
     * Parses the input command and returns the command type from Command.
     *
     * @param arg string
     * @return Command
     */
    public static Command parse(final String arg) {
        Command result = Command.UNKNOWNCOMMAND;
        for (Command command : Command.values()) {
            if (Converter.findString(arg, command.getRegex(), true)) {
                result = command;
                break;
            }
        }

        return result;
    }

    /**
     * Returns a list of all commands with a description.
     *
     * @return list of strings
     */
    public static ArrayList<String> getHelpMessage() {
        ArrayList<String> result = new ArrayList<String>();
        for (Command command : Command.values()) {
            if (!command.description.equals("")) {
                result.add(command.commandName + " - " + command.description);
            }
        }

        return result;
    }

    /**
     * Returns a command name.
     *
     * @return string
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Returns a command description.
     *
     * @return string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a regex filter for a command.
     *
     * @return regular expression
     */
    public String getRegex() {
        return this.regex;
    }
}