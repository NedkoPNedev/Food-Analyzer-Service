package bg.sofia.uni.fmi.mjt.constants;

public abstract class ServerConstants {

    public static final String FOOD_COMMAND = "get-food";
    public static final String FOOD_REPORT_COMMAND = "get-food-report";
    public static final String FOOD_BY_BARCODE_COMMAND = "get-food-by-barcode";
    public static final String CONNECT_COMMAND = "connect";
    public static final String ADD_TO_DAILY_INTAKE_COMMAND = "add-to-daily-intake";
    public static final String REMOVE_FROM_DAILY_INTAKE_COMMAND = "remove-from-daily-intake";
    public static final String SHOW_DAILY_INTAKE_COMMAND = "show-daily-intake";

    public static final String RESPONSE_ERROR = "Error while waiting for response!";

    public static final String FOOD_COMMAND_REQUEST_FORMAT =
            "https://api.nal.usda.gov/ndb/search/?q=%s&api_key=tlY43Jc90UZ73oE2hxEaHhqeJ5lJJhzP9F5FyFeg";
    public static final String FOOD_REPORT_COMMAND_REQUEST_FORMAT =
            "https://api.nal.usda.gov/ndb/V2/reports?ndbno=%s&type=b&format=json" +
                    "&api_key=tlY43Jc90UZ73oE2hxEaHhqeJ5lJJhzP9F5FyFeg";
    public static final String FOOD_BARCODE_COMMAND_REQUEST_FORMAT = "https://zxing.org/w/decode?u=%s";

    public static final String FOOD_NOT_FOUND_MESSAGE = "Food with name %s is not found!" + System.lineSeparator();
    public static final String NDBNO_NOT_FOUND_MESSAGE = "Food with ndbno %s is not found!" + System.lineSeparator();
    public static final String BARCODE_NOT_FOUND_MESSAGE = "Food with barcode %s is not found!" +
            System.lineSeparator();
    public static final String IMG_NOT_FOUND_MESSAGE = "Img : %s is not found!" + System.lineSeparator();

    public static final String UPC = "upc";
    public static final String IMG = "img";
    public static final String ERROR = "error";
    public static final String TEMP_DESTINATION = "temp";

    public static final String LIST_FIELD = "list";
    public static final String ITEM_FIELD = "item";
    public static final String NAME_FIELD = "name";
    public static final String NDBNO_FIELD = "ndbno";
    public static final String FOODS_FIELD = "foods";
    public static final String FOOD_FIELD = "food";
    public static final String DESC_FIELD = "desc";
    public static final String ING_FIELD = "ing";
    public static final String VALUE_FIELD = "value";
    public static final String UNIT_FIELD = "unit";
    public static final String NUTRIENTS_FIELD = "nutrients";

    public static final String FULL_FOOD_NAME = "Full food name : ";
    public static final String DATABASE_NUMBER = "Database number : ";
    public static final String INGREDIENTS = "Ingredients : ";

    public static final String FOOD_NAME_FILE_PATH = "database\\food-name\\%s";
    public static final String NDBNO_FILE_PATH = "database\\ndbno\\%s";
    public static final String BARCODE_FILE_PATH = "database\\barcode\\%s";

    public static final String CLIENT_DATABASE_PATH = "client-database\\%s";
    public static final String CLIENT_DAILY_INTAKE_PATH ="client-database\\%s\\%s";

    public static final String READING_ERROR = "Problem while reading from file %s!";
    public static final String WRITING_ERROR = "Problem while writing to file %s!";

    public static final String PRE_TAG = "<pre>";
    public static final String CLOSED_PRE_TAG = "</pre>";

    public static final String URL_SPACE = "%20";
    public static final String SPACES_REGEX= "\\s+";
    public static final String NUMBER_REGEX = "[^\\\\.[0-9]]";
    public static final String DATE_REGEX = "[^0-9-]";
    public static final String TWO_DIGITS = "%.2f";

    public static final int UPC_LEN = 5;
    public static final int IMG_LEN = 4;
    public static final int PRE_LEN = 5;
    public static final int USER_NAME_START_INDEX = 2;
    public static final int CHECK_COMMAND_LEN = 7;

    public static final String PRODUCT_ADDED = "Product with ndbno %s is successfully added to daily intake!" +
            System.lineSeparator();
    public static final String PRODUCT_REMOVED = "Product with ndbno %s is removed from daily intake!" +
            System.lineSeparator();

    public static final String DAILY_INTAKE = "Your current daily intake is :";
    public static final String DAILY_INTAKE_NOT_FOUND_MESSAGE = "Daily intake for date %s is empty!" +
            System.lineSeparator();

    public static final String ENERGY = "Energy";
    public static final String PROTEIN = "Protein";
    public static final String FAT = "fat";
    public static final String CARBOHYDRATES = "Carbohydrate";
    public static final String FIBER = "Fiber";
    public static final String KCAL = "kcal";
    public static final String GRAM = "g";
    public static final String FATS = "Fat";

    public static final String A_3 = "%3A";
    public static final String F_2 = "%2F";
    public static final String C_2 = "%2C";
    public static final String B_2 = "%2B";
    public static final String B_3 = "%3B";

    public static final String MISSING_NDBNO = "Ndbno is missing!" + System.lineSeparator();
    public static final String MISSING_DATE = "Date is missing!" + System.lineSeparator();

    public static final int CAPACITY = 32768;
    public static final int PORT = 4444;

    public static final String CLOSING_RESOURCES_PROBLEM = "Problem with closing server resources!";
    public static final String CHANNEL_ACCEPT_MESSAGE = "Problem with accepting the channel!";
    public static final String SERVER_PROBLEM = "Server problem on port %s!";
    public static final String SOCKET_CHANNEL_PROBLEM = "Problem with Socket Channel operations!" +
            "Exception message : %s";

    public static final String NO_COMMAND_MESSAGE = "No command entered!";
    public static final String CONNECTION_COMMAND = "connect";

    public static final String CLIENT_DISCONNECTED = "You are disconnected from the server!";
    public static final String DISCONNECT = "disconnect";
    public static final String CLIENT_CONNECTED = "%s, you have connected successfully!" + System.lineSeparator();
    public static final String EMPTY_FILE = "Empty file!";
    public static final String DIRECTORY_NOT_CREATED_MESSAGE =
            "Directory for user : %s is not created! Please try again!" + System.lineSeparator();

    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String CHECK_COMMAND = "check -";
    public static final String EMPTY_RESPONSE = "Empty response!";
}
