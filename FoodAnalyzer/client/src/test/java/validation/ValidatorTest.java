package validation;

import bg.sofia.uni.fmi.mjt.validation.Validator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static constants.ClientTestConstants.*;

public class ValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setup() {
        validator = Validator.getInstance();
    }

    @Test
    public void testCommandConnectValid() {
        Assert.assertTrue(validator.isConnectCommand(VALID_CONNECT_COMMAND));
    }

    @Test
    public void testCommandConnectInvalid() {
        Assert.assertFalse(validator.isConnectCommand(INVALID_CONNECT_COMMAND));
    }

    @Test
    public void testCommandFoodNameValid() {
        Assert.assertTrue(validator.isValidCommand(VALID_GET_FOOD_COMMAND));
    }

    @Test
    public void testCommandFoodNameInvalid() {
        Assert.assertFalse(validator.isValidCommand(INVALID_GET_FOOD_COMMAND));
    }

    @Test
    public void testCommandFoodReportValid() {
        Assert.assertTrue(validator.isValidCommand(VALID_GET_FOOD_REPORT_COMMAND));
    }

    @Test
    public void testCommandFoodReportInvalid() {
        Assert.assertFalse(validator.isValidCommand(INVALID_GET_FOOD_REPORT_COMMAND));
    }

    @Test
    public void testCommandFoodBarcodeValid() {
        Assert.assertTrue(validator.isValidCommand(VALID_GET_FOOD_BARCODE_COMMAND));
    }

    @Test
    public void testCommandFoodBarcodeInvalid() {
        Assert.assertFalse(validator.isValidCommand(INVALID_GET_FOOD_BARCODE_COMMAND));
    }

    @Test
    public void testCommandDailyIntakeAdditionValid() {
        Assert.assertTrue(validator.isValidCommand(VALID_DAILY_INTAKE_ADDITION_COMMAND));
    }

    @Test
    public void testCommandDailyIntakeAdditionInvalid() {
        Assert.assertFalse(validator.isValidCommand(INVALID_DAILY_INTAKE_ADDITION_COMMAND));
    }

    @Test
    public void testCommandDailyIntakeRemovalValid() {
        Assert.assertTrue(validator.isValidCommand(VALID_DAILY_INTAKE_REMOVAL_COMMAND));
    }

    @Test
    public void testCommandDailyIntakeRemovalInvalid() {
        Assert.assertFalse(validator.isValidCommand(INVALID_DAILY_INTAKE_REMOVAL_COMMAND));
    }

    @Test
    public void testCommandDailyIntakeTableValid() {
        Assert.assertTrue(validator.isValidCommand(VALID_DAILY_INTAKE_TABLE_COMMAND));
    }

    @Test
    public void testCommandDailyIntakeTableInvalid() {
        Assert.assertFalse(validator.isValidCommand(INVALID_DAILY_INTAKE_TABLE_COMMAND));
    }
}