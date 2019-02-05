package bg.sofia.uni.fmi.mjt.constants;

public abstract class ClientConstants {

    public static final int CAPACITY = 32768;
    public static final int CONNECT_COMMAND_LENGTH = 4;
    public static final int MAX_PORT_NUM = 65535;

    public static final String CONNECT = "connect";
    public static final String DISCONNECT = "disconnect";

    public static final String CLIENT_DISCONNECTED = "You are disconnected from the server!";
    public static final String RESPONSE_PROBLEM = "Problem with server response on socket channel : %s!";
    public static final String SOCKET_CHANNEL_PROBLEM =
            "Socket channel problem!" + System.lineSeparator() + "port : %s  host : %s." + "Try to connect again!";

    public static final String REGEX = "[0-9]+";
    public static final String SPACE_REGEX = "\\s+";

    public static final String WRONG_COMMAND = "Command is invalid! Please try again!" + System.lineSeparator();
    public static final String SOCKET_CHANNEL_WRITING_PROBLEM =
            "Problem with writing to socket channel command : %s!" + System.lineSeparator();

    public static final String GET_FOOD_COMMAND = "get-food ";
    public static final String GET_FOOD_REPORT_COMMAND = "get-food-report ";
    public static final String GET_FOOD_BY_BARCODE_COMMAND = "get-food-by-barcode ";
    public static final String UPC_CODE = "--upc=";
    public static final String IMG = "--img=";
    public static final String ADD_TO_DAILY_INTAKE_COMMAND = "add-to-daily-intake ";
    public static final String REMOVE_FROM_DAILY_INTAKE_COMMAND = "remove-from-daily-intake ";
    public static final String SHOW_DAILY_INTAKE_COMMAND = "show-daily-intake ";

    public static final String WELCOME_MESSAGE = "~ Welcome to the Food Analyzer service! ~" +
            System.lineSeparator();

    public static final String COMMANDS_LIST = "Here is the list of commands (Server is listening on port : 4444) :" +
            System.lineSeparator() +
            "* connect <host> <port> <username>"  + System.lineSeparator() +
            "* get-food <food-name>" + System.lineSeparator() +
            "* get-food-report <ndbno>" + System.lineSeparator() +
            "* get-food-by-barcode --upc=<upc_code>|--img=<barcode_image_file>" + System.lineSeparator() +
            "* add-to-daily-intake <ndbno>" + System.lineSeparator() +
            "* remove-from-daily-intake <ndbno>" + System.lineSeparator() +
            "* show-daily-intake <dd-mm-yyyy>"  + System.lineSeparator() +
            "* disconnect"  + System.lineSeparator();

    public static final String IMPORTANT_MESSAGE = "First you have to connect to the server via 'connect' command!" +
            System.lineSeparator();

    public static final int CLIENT_THREAD_WAIT_TIME = 100;
    public static final int USER_NAME_START_INDEX = 3;

    public static final String THREAD_INTERRUPTED = "Client thread is interrupted!" + System.lineSeparator();
    public static final String CLIENT_ALREADY_CONNECTED =
            "Client with username %s is already connected to the server! Please, try again!" + System.lineSeparator();
    public static final String SPACES_REGEX = "\\s+";
    public static final String TRUE = "true";
    public static final String CHECK_COMMAND = "check -";
}
