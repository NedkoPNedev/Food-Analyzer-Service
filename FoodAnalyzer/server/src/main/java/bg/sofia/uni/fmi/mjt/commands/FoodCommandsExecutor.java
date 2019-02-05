package bg.sofia.uni.fmi.mjt.commands;

import bg.sofia.uni.fmi.mjt.exceptions.*;

import java.net.http.HttpClient;
import java.util.List;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.SPACES_REGEX;

public class FoodCommandsExecutor {

    private static List<Command> commands;

    private FoodCommandsExecutor() {
        initialiseResources();
    }

    private void initialiseResources() {
        HttpClient client = HttpClient.newHttpClient();
        commands = List.of(new FoodNameCommand(client), new FoodReportCommand(client),
                new FoodBarcodeCommand(client), new ConnectCommand());
    }

    private static class ExecutorHelper {
        private static final FoodCommandsExecutor INSTANCE = new FoodCommandsExecutor();
    }

    public static FoodCommandsExecutor getInstance() {
        return ExecutorHelper.INSTANCE;
    }

    public String executeCommand(String command) throws FoodNotFoundException,
            NDBNONotFoundException, BarcodeNotFoundException, IMGNotFoundException, DailyIntakeFileNotFoundException {

        String commandName = command.substring(0, command.indexOf(" ")).replaceAll(SPACES_REGEX, "");
        String parameters = command.substring(command.indexOf(" ") + 1);
        String result = null;

        for (Command commandHandler : commands) {
            if (commandHandler.getCommandName().equals(commandName)) {
                result = commandHandler.executeCommand(parameters);
                break;
            }
        }
        return result;
    }
}