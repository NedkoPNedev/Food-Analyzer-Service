package bg.sofia.uni.fmi.mjt.commands.daily.intake.commands;

import bg.sofia.uni.fmi.mjt.commands.FoodCommandsExecutor;
import bg.sofia.uni.fmi.mjt.exceptions.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;

import static bg.sofia.uni.fmi.mjt.commands.constants.TestConstants.*;
import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class DailyIntakeCommandsExecutorTest {

    private static final String DAILY_INTAKE_CONTENT = SHOW_DAILY_INTAKE_COMMAND + " " +
            getDate(LocalDateTime.now()) + CLIENT;

    private static DailyIntakeCommandsExecutor dailyIntakeCommandsExecutor;

    @BeforeClass
    public static void setup() throws BarcodeNotFoundException, NDBNONotFoundException, FoodNotFoundException,
            DailyIntakeFileNotFoundException, IMGNotFoundException {
        dailyIntakeCommandsExecutor = DailyIntakeCommandsExecutor.getInstance();
        FoodCommandsExecutor foodCommandsExecutor;
        foodCommandsExecutor = FoodCommandsExecutor.getInstance();
        foodCommandsExecutor.executeCommand(CLIENT_CREATION);
        dailyIntakeCommandsExecutor.executeCommand(NDBNO_ADDED);
    }

    @Test
    public void testDailyIntakeAdditionCommandNDBNOFileAdded() {
        String actual;
        try {
            actual = dailyIntakeCommandsExecutor.executeCommand(EXISTING_NDBNO);
        } catch (NDBNONotFoundException | DailyIntakeFileNotFoundException | FoodNotFoundException |
                BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(PRODUCT_ADDED_MESSAGE.trim(), actual.trim());
    }

    @Test
    public void testDailyIntakeAdditionCommandNDBNONotFoundExceptionThrown() {
        String actual;
        try {
            actual = dailyIntakeCommandsExecutor.executeCommand(NEW_NDBNO_SEARCH);
        } catch (NDBNONotFoundException | DailyIntakeFileNotFoundException | FoodNotFoundException |
                BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(String.format(NDBNO_NOT_FOUND_MESSAGE, NDBNO).trim(), actual.trim());
    }

    @Test
    public void testDailyIntakeRemovalCommandFileRemovedSuccessfully() {
        String actual;
        try {
            actual = dailyIntakeCommandsExecutor.executeCommand(CLIENT_NDBNO_REMOVE);
        } catch (NDBNONotFoundException | DailyIntakeFileNotFoundException | FoodNotFoundException |
                BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(PRODUCT_REMOVED_MESSAGE.trim(), actual.trim());
    }

    @Test
    public void testDailyIntakeRemovalCommandNDBNONotFoundExceptionThrown() {
        String actual;
        try {
            actual = dailyIntakeCommandsExecutor.executeCommand(CLIENT_NDBNO_NOT_FOUND);
        } catch (NDBNONotFoundException | DailyIntakeFileNotFoundException | FoodNotFoundException |
                BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(String.format(NDBNO_NOT_FOUND_MESSAGE, NDBNO).trim(), actual.trim());
    }

    @Test
    public void testDailyIntakeTableCommandFullDailyIntakeContent() {
        File dailyIntakeFile = new File(String.format(CLIENT_DAILY_INTAKE_PATH,
                CLIENT_NAME, getDate(LocalDateTime.now())));
        dailyIntakeFile.delete();
        String actual;
        try {
            dailyIntakeCommandsExecutor.executeCommand(NDBNO_ADDED);
            dailyIntakeCommandsExecutor.executeCommand(OTHER_NDBNO_ADDED);
            actual = dailyIntakeCommandsExecutor.executeCommand(DAILY_INTAKE_CONTENT);
        } catch (NDBNONotFoundException | DailyIntakeFileNotFoundException | FoodNotFoundException |
                BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(CURRENT_DAILY_INTAKE.trim(), actual.trim());
    }

    @Test
    public void testDailyIntakeTableCommandDailyIntakeFileNotFoundExceptionThrown() {
        String actual;
        try {
            actual = dailyIntakeCommandsExecutor.executeCommand(DATE_NOT_IN_DATABASE);
        } catch (NDBNONotFoundException | DailyIntakeFileNotFoundException | FoodNotFoundException |
                BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(DAILY_INTAKE_NOT_FOUND.trim(), actual.trim());
    }

    private static String getDate(LocalDateTime date) {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
    }

    @AfterClass
    public static void cleanup() {
        File clientNewFile = new File(String.format(NEW_CLIENT_FILE, getDate(LocalDateTime.now())));
        File clientDirectory = new File(CLIENT_DIRECTORY_PATH);
        clientNewFile.delete();
        clientDirectory.delete();
    }
}