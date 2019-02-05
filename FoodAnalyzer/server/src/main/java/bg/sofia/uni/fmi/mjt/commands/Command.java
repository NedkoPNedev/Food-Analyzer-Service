package bg.sofia.uni.fmi.mjt.commands;

import bg.sofia.uni.fmi.mjt.exceptions.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public interface Command {

    String executeCommand(String command) throws FoodNotFoundException,
            NDBNONotFoundException, BarcodeNotFoundException, IMGNotFoundException, DailyIntakeFileNotFoundException;

    String getCommandName();

    default HttpRequest getRequest(String parameter) {
        return HttpRequest.newBuilder()
                .uri(URI.create(parameter))
                .build();
    }

    default String getResponse(HttpClient client, HttpRequest request, Logger logger) {
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (InterruptedException | IOException e) {
            logger.error(RESPONSE_ERROR, e);
            return EMPTY_RESPONSE;
        }
    }

    default String getUsername(String parameters) {
        int usernamePosition = getUsernamePosition(parameters);
        return parameters.substring(usernamePosition).replaceAll(SPACES_REGEX, "");
    }

    private int getUsernamePosition(String parameters) {
        int index = getFirstDigit(parameters);
        while (parameters.charAt(index) == ' ' || parameters.charAt(index) == '-' ||
                (parameters.charAt(index) >= '0' && parameters.charAt(index) <= '9')) {
            index++;
        }
        return index;
    }

    private int getFirstDigit(String parameters) {
        int index = 0;
        while (parameters.charAt(index) < '0' || parameters.charAt(index) > '9') {
            index++;
        }
        return index;
    }
}
