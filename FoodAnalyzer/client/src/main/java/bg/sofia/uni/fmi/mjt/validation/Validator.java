package bg.sofia.uni.fmi.mjt.validation;

import static bg.sofia.uni.fmi.mjt.constants.ClientConstants.*;

public class Validator {

    private static Validator ourInstance = new Validator();

    public static Validator getInstance() {
        return ourInstance;
    }

    private Validator() {
    }

    public boolean isConnectCommand(String command) {
        String[] commands = command.split(SPACE_REGEX);

        return commands.length >= CONNECT_COMMAND_LENGTH &&
                commands[0].equalsIgnoreCase(CONNECT) &&
                commands[2].matches(REGEX) &&
                Integer.parseInt(commands[2]) >= 0 &&
                Integer.parseInt(commands[2]) < MAX_PORT_NUM;
    }

    public boolean isValidCommand(String consoleInput) {
        return (consoleInput.contains(" ") &&
                (consoleInput.contains(GET_FOOD_COMMAND) || consoleInput.contains(GET_FOOD_REPORT_COMMAND) ||
                        (consoleInput.contains(GET_FOOD_BY_BARCODE_COMMAND) &&
                                (consoleInput.contains(UPC_CODE) || consoleInput.contains(IMG))) ||
                        consoleInput.contains(ADD_TO_DAILY_INTAKE_COMMAND) ||
                        consoleInput.contains(REMOVE_FROM_DAILY_INTAKE_COMMAND) ||
                        consoleInput.contains(REMOVE_FROM_DAILY_INTAKE_COMMAND) ||
                        consoleInput.contains(SHOW_DAILY_INTAKE_COMMAND))) ||
                consoleInput.contains(DISCONNECT);
    }
}
