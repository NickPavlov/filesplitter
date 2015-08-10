package com.sysgears.filesplitter.model.commands;

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
     * @param name a command name
     */
    Commands(final String name) {
        this.name = name;
    }

    /**
     * Parses the input command and returns the command from the <code>Commands</code>.
     *
     * @param expression input expression
     * @return parsed command
     */
    public static Commands parse(final String expression) {
        Commands result = Commands.UNKNOWN_COMMAND;
        /*
        List<Commands> commands = new ArrayList<Commands>(Arrays.asList(Commands.values()));
        Iterator<Commands> iterator = commands.iterator();
        boolean matches = false;
        Commands currentCommand;
        while (iterator.hasNext() && !matches) {
            currentCommand = iterator.next();
            matches = Pattern.compile(currentCommand.getRegex()).matcher(expression).find();
            if (matches) {
                result = currentCommand;
            }
        }
        */

        /*
        Commands result = Commands.UNKNOWN_COMMAND;
        for (Commands command : Commands.values()) {
            if (Pattern.compile(currentCommand.getRegex()).matcher(expression).find()) {
                result = command;
                break;
            }
        }
        */

        return result;
    }

    /**
     * Returns a command name.
     *
     * @return a command name
     */
    public String getName() {
        return name;
    }
}