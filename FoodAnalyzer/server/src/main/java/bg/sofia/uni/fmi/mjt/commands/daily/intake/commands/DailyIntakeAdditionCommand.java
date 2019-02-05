package bg.sofia.uni.fmi.mjt.commands.daily.intake.commands;

import bg.sofia.uni.fmi.mjt.commands.Command;
import bg.sofia.uni.fmi.mjt.exceptions.NDBNONotFoundException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class DailyIntakeAdditionCommand implements Command {

    private static Logger logger = Logger.getLogger(DailyIntakeAdditionCommand.class);

    @Override
    public String executeCommand(String parameters) throws NDBNONotFoundException {
        String ndbno = parameters.replaceAll(NUMBER_REGEX, "");
        if (ndbno.length() == 0) {
            throw new NDBNONotFoundException(MISSING_NDBNO);
        }
        String username = getUsername(parameters);
        return addToFileSystem(ndbno, username);
    }

    private String addToFileSystem(String ndbno, String username) throws NDBNONotFoundException {
        File ndbnoFile = new File(String.format(NDBNO_FILE_PATH, ndbno));
        if (ndbnoFile.exists()) {
            try (FileWriter fileWriter = new FileWriter(new File(String.format(CLIENT_DAILY_INTAKE_PATH, username,
                    getDate(LocalDateTime.now()))), true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                bufferedWriter.write(ndbnoFile.getPath() + System.lineSeparator());
                bufferedWriter.flush();
            } catch (IOException e) {
                BasicConfigurator.configure();
                logger.error(String.format(WRITING_ERROR, ndbnoFile.getPath()), e);
                return EMPTY_FILE;
            }
            return String.format(PRODUCT_ADDED, ndbno);
        } else {
            throw new NDBNONotFoundException(String.format(NDBNO_NOT_FOUND_MESSAGE, ndbno));
        }
    }

    private String getDate(LocalDateTime date) {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
    }

    @Override
    public String getCommandName() {
        return ADD_TO_DAILY_INTAKE_COMMAND;
    }
}
