package bg.sofia.uni.fmi.mjt.commands;

import java.io.File;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class ConnectCommand implements Command {

    @Override
    public String executeCommand(String parameters) {
        String username = getParsedUsername(parameters);
        File usernameFile = new File(String.format(CLIENT_DATABASE_PATH,
                username.replaceAll(SPACES_REGEX, "")));

        boolean isFileCreated = usernameFile.mkdir();
        return (!isFileCreated && !usernameFile.exists()) ? String.format(DIRECTORY_NOT_CREATED_MESSAGE, username) :
                String.format(CLIENT_CONNECTED, username);
    }

    private String getParsedUsername(String parameter) {
        String[] parameters = parameter.split(SPACES_REGEX);
        StringBuilder username = new StringBuilder();
        for (int i = USER_NAME_START_INDEX; i < parameters.length - 1; i++) {
            username.append(parameters[i]).append(" ");
        }
        username.append(parameters[parameters.length - 1]);
        return username.toString();
    }

    @Override
    public String getCommandName() {
        return CONNECT_COMMAND;
    }
}
