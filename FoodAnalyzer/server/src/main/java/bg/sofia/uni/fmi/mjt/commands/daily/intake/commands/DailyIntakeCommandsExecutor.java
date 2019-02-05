package bg.sofia.uni.fmi.mjt.commands.daily.intake.commands;

import bg.sofia.uni.fmi.mjt.commands.Command;
import bg.sofia.uni.fmi.mjt.exceptions.*;

import java.util.List;

public class DailyIntakeCommandsExecutor {

    private List<Command> dailyIntakeCommands;

    private DailyIntakeCommandsExecutor() {
        initialiseResources();
    }

    private void initialiseResources() {
        dailyIntakeCommands = List.of(new DailyIntakeAdditionCommand(),
                new DailyIntakeRemovalCommand(), new DailyIntakeTableCommand());
    }

    private static class DailyIntakeExecutorHelper {
        private static final DailyIntakeCommandsExecutor INSTANCE = new DailyIntakeCommandsExecutor();
    }

    public static DailyIntakeCommandsExecutor getInstance() {
        return DailyIntakeExecutorHelper.INSTANCE;
    }

    public String executeCommand(String command) throws NDBNONotFoundException, DailyIntakeFileNotFoundException,
            BarcodeNotFoundException, IMGNotFoundException, FoodNotFoundException {

        String commandName = command.substring(0, command.indexOf(" "));
        String parameters = command.substring(command.indexOf(" ") + 1);
        String result = null;

        for (Command commandHandler : dailyIntakeCommands) {
            if (commandHandler.getCommandName().equals(commandName)) {
                result = commandHandler.executeCommand(parameters);
                break;
            }
        }
        return result;
    }
}
