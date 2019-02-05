package bg.sofia.uni.fmi.mjt.commands;

import bg.sofia.uni.fmi.mjt.exceptions.BarcodeNotFoundException;
import bg.sofia.uni.fmi.mjt.exceptions.IMGNotFoundException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class FoodBarcodeCommand implements Command {

    private static Logger logger = Logger.getLogger(FoodBarcodeCommand.class);

    private HttpClient client;

    FoodBarcodeCommand(HttpClient client) {
        this.client = client;
        BasicConfigurator.configure();
    }

    @Override
    public String executeCommand(String parameters) throws BarcodeNotFoundException, IMGNotFoundException {
        String trimmedParameters = parameters.replaceAll(SPACES_REGEX, "");
        String code = getCode(trimmedParameters);
        return getBarcodeReport(code);
    }

    private String getCode(String parameters) throws IMGNotFoundException {
        if (parameters.contains(UPC)) {
            if (parameters.contains("|")) {
                return parameters.substring(parameters.indexOf(UPC) + UPC_LEN - 1, parameters.indexOf("|"));
            }
            return parameters.substring(parameters.indexOf(UPC) + UPC_LEN - 1);
        }
        String imgAsString = parameters.substring(parameters.indexOf(IMG) + IMG_LEN);
        return getCodeFromImage(imgAsString);
    }

    private String getCodeFromImage(String imgAsString) throws IMGNotFoundException {
        HttpRequest request = getRequest(String.format(FOOD_BARCODE_COMMAND_REQUEST_FORMAT, getImageURL(imgAsString)));
        String responseBody = getResponse(client, request, logger);
        if (responseBody.contains(PRE_TAG)) {
            return responseBody.substring(responseBody.indexOf(PRE_TAG) + PRE_LEN,
                    responseBody.indexOf(CLOSED_PRE_TAG));
        } else {
            throw new IMGNotFoundException(String.format(IMG_NOT_FOUND_MESSAGE, imgAsString));
        }
    }

    private String getImageURL(String img) {
        return img.replaceAll(":", A_3).replaceAll("/", F_2)
                .replaceAll(";", B_3).replaceAll(",", C_2)
                .replaceAll("\\+", B_2);
    }

    private String getBarcodeReport(String code) throws BarcodeNotFoundException {
        File barcodeFile = new File(String.format(BARCODE_FILE_PATH, code));
        if (barcodeFile.exists()) {
            try {
                return Files.readString(barcodeFile.toPath(), Charset.defaultCharset());
            } catch (IOException e) {
                logger.error(String.format(READING_ERROR, barcodeFile.getPath()), e);
                return EMPTY_FILE;
            }
        } else {
            throw new BarcodeNotFoundException(String.format(BARCODE_NOT_FOUND_MESSAGE, code));
        }
    }

    @Override
    public String getCommandName() {
        return FOOD_BY_BARCODE_COMMAND;
    }
}
