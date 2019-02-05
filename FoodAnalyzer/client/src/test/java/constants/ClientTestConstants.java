package constants;

public abstract class ClientTestConstants {

    public static final String VALID_CONNECT_COMMAND = "connect localhost 4444 Nedko Nedev";
    public static final String INVALID_CONNECT_COMMAND = "connect 4444 Nedko";
    public static final String VALID_GET_FOOD_COMMAND = "get-food pasta";
    public static final String INVALID_GET_FOOD_COMMAND = "get-food";
    public static final String VALID_GET_FOOD_REPORT_COMMAND = "get-food-report 454827";
    public static final String INVALID_GET_FOOD_REPORT_COMMAND = "get-food-report454214";
    public static final String VALID_GET_FOOD_BARCODE_COMMAND = "get-food-by-barcode --upc=00899390320492";
    public static final String INVALID_GET_FOOD_BARCODE_COMMAND = "get-food-by-barcode -im=";
    public static final String VALID_DAILY_INTAKE_ADDITION_COMMAND = "add-to-daily-intake 34958393";
    public static final String INVALID_DAILY_INTAKE_ADDITION_COMMAND = "add-to-daily intake ";
    public static final String VALID_DAILY_INTAKE_REMOVAL_COMMAND = "remove-from-daily-intake 32854230";
    public static final String INVALID_DAILY_INTAKE_REMOVAL_COMMAND = "remove-from daily intake ";
    public static final String VALID_DAILY_INTAKE_TABLE_COMMAND = "show-daily-intake 1-2-2019";
    public static final String INVALID_DAILY_INTAKE_TABLE_COMMAND = "show daily-intake ";
}
