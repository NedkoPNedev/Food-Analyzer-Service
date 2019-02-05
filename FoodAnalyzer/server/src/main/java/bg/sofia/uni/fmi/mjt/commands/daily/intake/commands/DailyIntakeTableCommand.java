package bg.sofia.uni.fmi.mjt.commands.daily.intake.commands;

import bg.sofia.uni.fmi.mjt.commands.Command;
import bg.sofia.uni.fmi.mjt.exceptions.DailyIntakeFileNotFoundException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class DailyIntakeTableCommand implements Command {

    private static Logger logger = Logger.getLogger(DailyIntakeTableCommand.class);

    @Override
    public String executeCommand(String parameters) throws DailyIntakeFileNotFoundException {
        String date = parameters.replaceAll(DATE_REGEX, "");
        if (date.length() == 0) {
            throw new DailyIntakeFileNotFoundException(MISSING_DATE);
        }
        String username = getUsername(parameters);
        return getDailyIntakeTable(date, username);
    }

    private String getDailyIntakeTable(String date, String username) throws DailyIntakeFileNotFoundException {
        File dailyIntakeFile = new File(String.format(CLIENT_DAILY_INTAKE_PATH, username, date));
        if (dailyIntakeFile.exists()) {
            return getIntake(dailyIntakeFile);
        } else {
            throw new DailyIntakeFileNotFoundException(String.format(DAILY_INTAKE_NOT_FOUND_MESSAGE, date));
        }
    }

    private String getIntake(File dailyIntakeFile) {
        double energy = 0.0, protein = 0.0, fat = 0.0, carbohydrate = 0.0, fiber = 0.0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(dailyIntakeFile))) {
            String currentFilePath;
            while ((currentFilePath = fileReader.readLine()) != null) {
                try (BufferedReader foodReader = new BufferedReader(new FileReader(currentFilePath))) {
                    String currentLine;
                    while ((currentLine = foodReader.readLine()) != null) {
                        if (currentLine.contains(ENERGY)) {
                            energy += getNumber(currentLine);
                        } else if (currentLine.contains(PROTEIN)) {
                            protein += getNumber(currentLine);
                        } else if (currentLine.contains(FAT)) {
                            fat += getNumber(currentLine);
                        } else if (currentLine.contains(CARBOHYDRATES)) {
                            carbohydrate += getNumber(currentLine);
                        } else if (currentLine.contains(FIBER)) {
                            fiber += getNumber(currentLine);
                        }
                    }
                }
            }
        } catch (IOException e) {
            BasicConfigurator.configure();
            logger.error(String.format(READING_ERROR, dailyIntakeFile.getPath()), e);
        }
        return constructIntakeString(energy, protein, fat, carbohydrate, fiber);
    }

    private String constructIntakeString(double energy, double protein, double fat, double carbohydrate, double fiber) {
        return DAILY_INTAKE + System.lineSeparator() + ENERGY + " : " + getUpToTwoDigits(energy) + " " + KCAL +
                System.lineSeparator() +
                PROTEIN + " : " + getUpToTwoDigits(protein) + " " + GRAM + System.lineSeparator() +
                FATS + " : " + getUpToTwoDigits(fat) + " " + GRAM + System.lineSeparator() +
                CARBOHYDRATES + " : " + getUpToTwoDigits(carbohydrate) + " " + GRAM + System.lineSeparator() +
                FIBER + " : " + getUpToTwoDigits(fiber) + " " + GRAM + System.lineSeparator();
    }

    private String getUpToTwoDigits(double number) {
        return String.format(TWO_DIGITS, number);
    }

    private double getNumber(String currentLine) {
        return Double.parseDouble(currentLine.replaceAll(NUMBER_REGEX,""));
    }

    @Override
    public String getCommandName() {
        return SHOW_DAILY_INTAKE_COMMAND;
    }
}
