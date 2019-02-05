package bg.sofia.uni.fmi.mjt.commands.daily.intake.commands;

import bg.sofia.uni.fmi.mjt.commands.Command;
import bg.sofia.uni.fmi.mjt.exceptions.NDBNONotFoundException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class DailyIntakeRemovalCommand implements Command {

    private static Logger logger = Logger.getLogger(DailyIntakeRemovalCommand.class);

    @Override
    public String executeCommand(String parameters) throws NDBNONotFoundException {
        String ndbno = parameters.replaceAll(NUMBER_REGEX, "");
        if (ndbno.length() == 0) {
            throw new NDBNONotFoundException(MISSING_NDBNO);
        }
        String removedContent = String.format(NDBNO_FILE_PATH, ndbno);
        String username = getUsername(parameters);

        boolean lineRemoved = isLineRemoved(username, removedContent);
        if (lineRemoved) {
            return String.format(PRODUCT_REMOVED, ndbno);
        } else {
            throw new NDBNONotFoundException(String.format(NDBNO_NOT_FOUND_MESSAGE, ndbno));
        }
    }

    private boolean isLineRemoved(String username, String removedContent) {
        File dailyIntakeFile = new File(String.format(
                CLIENT_DAILY_INTAKE_PATH, username, getDate(LocalDateTime.now())));
        File tempFile = new File(String.format(CLIENT_DAILY_INTAKE_PATH, username, TEMP_DESTINATION));

        boolean lineRemoved = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(dailyIntakeFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(removedContent)) {
                    lineRemoved = true;
                    continue;
                }
                writer.write(currentLine + System.lineSeparator());
                writer.flush();
            }
        } catch (IOException e) {
            BasicConfigurator.configure();
            logger.error(String.format(READING_ERROR, dailyIntakeFile.getPath()), e);
            return false;
        }
        dailyIntakeFile.delete();
        tempFile.renameTo(dailyIntakeFile);
        return lineRemoved;
    }

    private String getDate(LocalDateTime date) {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
    }

    @Override
    public String getCommandName() {
        return REMOVE_FROM_DAILY_INTAKE_COMMAND;
    }
}