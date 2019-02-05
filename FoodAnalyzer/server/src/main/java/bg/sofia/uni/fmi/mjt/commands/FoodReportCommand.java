package bg.sofia.uni.fmi.mjt.commands;

import bg.sofia.uni.fmi.mjt.exceptions.NDBNONotFoundException;
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

public class FoodReportCommand implements Command {

    private static Logger logger = Logger.getLogger(FoodReportCommand.class);

    private HttpClient client;

    FoodReportCommand(HttpClient client) {
        this.client = client;
        BasicConfigurator.configure();
    }

    @Override
    public String executeCommand(String parameter) throws NDBNONotFoundException {
        String ndbnoURLName = parameter.replaceAll(SPACES_REGEX, URL_SPACE);
        String ndbnoFileName = parameter.replaceAll(SPACES_REGEX, "");

        return getFoodReport(ndbnoFileName, ndbnoURLName, parameter);
    }

    private String getFoodReport(String ndbnoFileName, String ndbnoURLName, String parameter)
            throws NDBNONotFoundException {

        File foodFile = new File(String.format(NDBNO_FILE_PATH, ndbnoFileName));
        if (foodFile.exists()) {
            return getFileInfo(foodFile);
        }

        String fileName = foodFile.getName();

        synchronized (fileName) {
            if (foodFile.exists()) {
                return getFileInfo(foodFile);
            }
            HttpRequest request = getRequest(String.format(FOOD_REPORT_COMMAND_REQUEST_FORMAT, ndbnoURLName));
            String response = getParsedJsonResponse(getResponse(client, request, logger), parameter);
            addToFileSystem(ndbnoFileName, response, NDBNO_FILE_PATH);
            addToBarcodeDirectory(response);
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

    private String getParsedJsonResponse(String jsonResponse, String parameter) throws NDBNONotFoundException {
        StringBuilder response = new StringBuilder();
        JSONObject object = new JSONObject(jsonResponse);
        if (object.getJSONArray(FOODS_FIELD).getJSONObject(0).has(ERROR)) {
            throw new NDBNONotFoundException(String.format(NDBNO_NOT_FOUND_MESSAGE, parameter));
        }
        JSONObject foodObject = object.getJSONArray(FOODS_FIELD)
                .getJSONObject(0)
                .getJSONObject(FOOD_FIELD);

        response.append(FULL_FOOD_NAME)
                .append(foodObject.getJSONObject(DESC_FIELD).getString(NAME_FIELD))
                .append(System.lineSeparator())
                .append(INGREDIENTS);

        if (foodObject.has(ING_FIELD)) {
            response.append(foodObject.getJSONObject(ING_FIELD).getString(DESC_FIELD));
        }
        response.append(System.lineSeparator());

        JSONArray nutrients = foodObject.getJSONArray(NUTRIENTS_FIELD);
        JSONObject currentNutrient;
        for (int i = 0; i < nutrients.length(); i++) {
            currentNutrient = nutrients.getJSONObject(i);
            response.append(currentNutrient.getString(NAME_FIELD))
                    .append(" : ")
                    .append(currentNutrient.getString(VALUE_FIELD))
                    .append(" ")
                    .append(currentNutrient.getString(UNIT_FIELD))
                    .append(System.lineSeparator());
        }
        return response.toString();
    }

    private void addToFileSystem(String parameter, String response, String path) {
        File foodFile = new File(String.format(path, parameter));
        try (PrintWriter foodWriter = new PrintWriter(foodFile)) {
            foodWriter.write(response);
        } catch (FileNotFoundException e) {
            logger.error(String.format(WRITING_ERROR, foodFile.getPath()), e);
        }
    }

    private void addToBarcodeDirectory(String response) {
        if (response.contains(UPC.toUpperCase())) {
            String barcode = response.substring(response.indexOf(UPC.toUpperCase()) + UPC_LEN,
                    response.indexOf(System.lineSeparator()));
            addToFileSystem(barcode, response, BARCODE_FILE_PATH);
        }
    }

    @Override
    public String getCommandName() {
        return FOOD_REPORT_COMMAND;
    }
}