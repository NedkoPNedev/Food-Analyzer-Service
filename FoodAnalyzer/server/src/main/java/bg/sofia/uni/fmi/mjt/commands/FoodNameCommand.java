package bg.sofia.uni.fmi.mjt.commands;

import bg.sofia.uni.fmi.mjt.exceptions.FoodNotFoundException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class FoodNameCommand implements Command{

    private static Logger logger = Logger.getLogger(FoodNameCommand.class);

    private HttpClient client;

    FoodNameCommand(HttpClient client) {
        this.client = client;
        BasicConfigurator.configure();
    }

    @Override
    public String executeCommand(String parameter) throws FoodNotFoundException {
        String foodURLName = parameter.replaceAll(SPACES_REGEX, URL_SPACE);
        String foodFileName = getFileName(parameter);

        return getNameReport(foodFileName, foodURLName, parameter);
    }


    private String getFileName(String parameter) {
        String fileName = parameter.replaceAll(SPACES_REGEX, "-");
        return (fileName.charAt(0) == '-') ? fileName.substring(1) : fileName;
    }

    private String getNameReport(String foodFileName, String foodURLName, String parameter)
            throws FoodNotFoundException {

        File foodFile = new File(String.format(FOOD_NAME_FILE_PATH, foodFileName));
        if (foodFile.exists()) {
            return getFileInfo(foodFile);
        }

        String fileName = foodFile.getName();

        synchronized (fileName) {
            if (foodFile.exists()) {
                return getFileInfo(foodFile);
            }
            HttpRequest request = getRequest(String.format(FOOD_COMMAND_REQUEST_FORMAT, foodURLName));
            String response = getParsedJsonResponse(getResponse(client, request, logger), parameter);
            addToFileSystem(foodFile, response);
            return response;
        }
    }

    private String getFileInfo(File foodFile) {
        try {
            return Files.readString(foodFile.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            logger.error(String.format(READING_ERROR, foodFile.getPath()), e);
            return EMPTY_FILE;
        }
    }

    private String getParsedJsonResponse(String jsonResponse, String parameter) throws FoodNotFoundException {
        JSONObject object = new JSONObject(jsonResponse);
        StringBuilder response = new StringBuilder();
        if (!object.has(LIST_FIELD)) {
            throw new FoodNotFoundException(String.format(FOOD_NOT_FOUND_MESSAGE, parameter));
        }
        JSONObject listObject = object.getJSONObject(LIST_FIELD);
        JSONArray foods = listObject.getJSONArray(ITEM_FIELD);
        for (int i = 0; i < foods.length(); i++) {
            response.append(FULL_FOOD_NAME)
                    .append(foods.getJSONObject(i).getString(NAME_FIELD))
                    .append(System.lineSeparator())
                    .append(DATABASE_NUMBER)
                    .append(foods.getJSONObject(i).getString(NDBNO_FIELD))
                    .append(System.lineSeparator());
        }
        return response.toString();
    }

    private void addToFileSystem(File foodFile, String response) {
        try (PrintWriter printWriter = new PrintWriter(foodFile)) {
            printWriter.write(response);
        } catch (FileNotFoundException e) {
            logger.error(String.format(WRITING_ERROR, foodFile.getPath()), e);
        }
    }

    @Override
    public String getCommandName() {
        return FOOD_COMMAND;
    }
}