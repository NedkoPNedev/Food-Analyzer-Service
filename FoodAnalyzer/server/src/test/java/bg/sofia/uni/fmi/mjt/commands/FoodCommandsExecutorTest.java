package bg.sofia.uni.fmi.mjt.commands;

import bg.sofia.uni.fmi.mjt.commands.http.response.StubbedHttpClient;
import bg.sofia.uni.fmi.mjt.exceptions.BarcodeNotFoundException;
import bg.sofia.uni.fmi.mjt.exceptions.FoodNotFoundException;
import bg.sofia.uni.fmi.mjt.exceptions.IMGNotFoundException;
import bg.sofia.uni.fmi.mjt.exceptions.NDBNONotFoundException;
import org.junit.*;

import java.io.File;
import java.net.http.HttpClient;

import static bg.sofia.uni.fmi.mjt.commands.constants.TestConstants.*;

public class FoodCommandsExecutorTest {

    @Test
    public void testFoodNameCommandAPIRequest() {
        StubbedHttpClient<String> client = new StubbedHttpClient<>(RAFFAELLO_JSON);
        FoodNameCommand foodNameCommand = new FoodNameCommand(client);
        String actual;
        try {
            actual = foodNameCommand.executeCommand(FOOD_RAFFAELLO);
        } catch (FoodNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(RAFFAELLO.trim(), actual.trim());
    }

    @Test
    public void testFoodNameCommandFileSystem() {
        HttpClient client = HttpClient.newHttpClient();
        FoodNameCommand foodNameCommand = new FoodNameCommand(client);
        String actual;
        try {
            actual = foodNameCommand.executeCommand(FOOD_ALPINE_MILKA);
        } catch (FoodNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(ALPINE_MILKA_FOODS.trim(), actual.trim());
    }

    @Test
    public void testFoodNameCommandExceptionHanding() {
        StubbedHttpClient<String> client = new StubbedHttpClient<>(INVALID_RESPONSE_JSON);
        FoodNameCommand foodNameCommand = new FoodNameCommand(client);
        String actual;
        try {
            actual = foodNameCommand.executeCommand(INVALID_GET_FOOD_COMMAND);
        } catch (FoodNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(FOOD_NOT_FOUND.trim(), actual.trim());
    }

    @Test
    public void testFoodReportCommandAPIRequest() {
        StubbedHttpClient<String> client = new StubbedHttpClient<>(NDBNO_RESPONSE_JSON);
        FoodReportCommand foodReportCommand = new FoodReportCommand(client);
        String actual;
        try {
            actual = foodReportCommand.executeCommand(FOOD_REPORT_NDBNO_CODE);
        } catch ( NDBNONotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(FOOD_REPORT_RESPONSE.trim(), actual.trim());
    }

    @Test
    public void testFoodReportCommandFileSystem() {
        HttpClient client = HttpClient.newHttpClient();
        FoodReportCommand foodReportCommand = new FoodReportCommand(client);
        String actual;
        try {
            actual = foodReportCommand.executeCommand(MILK_CHOCOLATE_NDBNO_CODE);
        } catch ( NDBNONotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(MILK_CHOCOLATE.trim(), actual.trim());
    }

    @Test
    public void testFoodReportCommandExceptionHanding() {
        StubbedHttpClient<String> client = new StubbedHttpClient<>(INVALID_REPORT_RESPONSE_JSON);
        FoodReportCommand foodReportCommand = new FoodReportCommand(client);
        String actual;
        try {
            actual = foodReportCommand.executeCommand(INVALID_GET_FOOD_REPORT_COMMAND);
        } catch (NDBNONotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(FOOD_REPORT_NOT_FOUND.trim(), actual.trim());
    }

    @Test
    public void testFoodByBarcodeCommandImg() {
        StubbedHttpClient<String> client = new StubbedHttpClient<>(IMG_RESPONSE);
        FoodBarcodeCommand foodBarcodeCommand = new FoodBarcodeCommand(client);
        String actual;
        try {
            actual = foodBarcodeCommand.executeCommand(IMG_COMMAND);
        } catch  (BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(MILK_CHOCOLATE, actual.trim());
    }

    @Test
    public void testFoodByBarcodeCommandImgNotFound() {
        StubbedHttpClient<String> client = new StubbedHttpClient<>(HTML_RESPONSE);
        FoodBarcodeCommand foodBarcodeCommand = new FoodBarcodeCommand(client);
        String actual;
        try {
            actual = foodBarcodeCommand.executeCommand(IMG_BARCODE_COMMAND);
        } catch (BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(IMG_BARCODE_NOT_FOUND.trim(), actual.trim());
    }

    @Test
    public void testFoodByBarcodeCommandUPCFound() {
        HttpClient client = HttpClient.newHttpClient();
        FoodBarcodeCommand foodBarcodeCommand = new FoodBarcodeCommand(client);
        String actual;
        try {
            actual = foodBarcodeCommand.executeCommand(MILK_CHOCOLATE_UPC_COMMAND);
        } catch (BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(MILK_CHOCOLATE.trim(), actual.trim());
    }

    @Test
    public void testFoodByBarcodeCommandUPCAndImg() {
        HttpClient client = HttpClient.newHttpClient();
        FoodBarcodeCommand foodBarcodeCommand = new FoodBarcodeCommand(client);
        String actual;
        try {
            actual = foodBarcodeCommand.executeCommand(MILK_CHOCOLATE_BY_BARCODE_COMMAND);
        } catch (BarcodeNotFoundException | IMGNotFoundException e) {
            actual = e.getMessage();
        }
        Assert.assertEquals(MILK_CHOCOLATE.trim(), actual.trim());
    }

    @Test
    public void testConnectCommandClientConnection() {
        ConnectCommand connectCommand = new ConnectCommand();
        String actual;
        actual = connectCommand.executeCommand(CONNECT_COMMAND_USER);
        Assert.assertEquals(CLIENT_CONNECTED_MESSAGE.trim(), actual.trim());
    }

    @AfterClass
    public static void cleanup() {
        File raffaelloFile = new File(RAFFAELLO_FILE_PATH);
        File ndbnoCodeFile = new File(NDBNO_CODE_FILE_PATH);
        File newClientFile = new File(NEW_CLIENT_FILE_PATH);

        raffaelloFile.delete();
        ndbnoCodeFile.delete();
        newClientFile.delete();
    }
}
