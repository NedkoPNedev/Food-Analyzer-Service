package bg.sofia.uni.fmi.mjt.commands.constants;

public abstract class TestConstants {

    public static final String RAFFAELLO_JSON = "{\n" +
            "  \"list\": {\n" +
            "    \"q\": \"raffaello\",\n" +
            "    \"sr\": \"1\",\n" +
            "    \"ds\": \"any\",\n" +
            "    \"start\": 0,\n" +
            "    \"end\": 1,\n" +
            "    \"total\": 1,\n" +
            "    \"group\": \"\",\n" +
            "    \"sort\": \"r\",\n" +
            "    \"item\": [\n" +
            "      {\n" +
            "        \"offset\": 0,\n" +
            "        \"group\": \"Branded Food Products Database\",\n" +
            "        \"name\": \"RAFFAELLO, ALMOND COCONUT TREAT, UPC: 009800146130\",\n" +
            "        \"ndbno\": \"45142036\",\n" +
            "        \"ds\": \"LI\",\n" +
            "        \"manu\": \"Ferrero U.S.A., Incorporated\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    public static final String INVALID_RESPONSE_JSON = "{\n" +
            "    \"errors\": {\n" +
            "        \"error\": [\n" +
            "            {\n" +
            "                \"status\": 400,\n" +
            "                \"parameter\": \"results\",\n" +
            "                \"message\": \"Your search resulted in zero results.Change your parameters and try " +
            "again\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    public static final String RAFFAELLO = "Full food name : RAFFAELLO, ALMOND COCONUT TREAT, UPC: 009800146130"
            + System.lineSeparator() + "Database number : 45142036" + System.lineSeparator();

    public static final String FOOD_RAFFAELLO = "raffaello";
    public static final String RAFFAELLO_FILE_PATH = "database\\food-name\\raffaello";
    public static final String NDBNO_CODE_FILE_PATH = "database\\ndbno\\45251917";
    public static final String FOOD_REPORT_NDBNO_CODE = "45251917";
    public static final String FOOD_ALPINE_MILKA = "alpine milka";
    public static final String INVALID_GET_FOOD_COMMAND = "invalid";

    public static final String ALPINE_MILKA_FOODS = "Full food name : MILKA, ALPINE MILK CHOCOLATE, " +
            "CHERRY CREAM, UPC: 891128148152" + System.lineSeparator() +
            "Database number : 45251917" + System.lineSeparator() +
            "Full food name : MILKA CHOCOLATE BAR ALPINE MILK 1X3.520 OZ, UNPREPARED, " +
            "GTIN: 00070221103026" + System.lineSeparator() +
            "Database number : 45368729" + System.lineSeparator() +
            "Full food name : MILKA CHOCOLATE PIECES ALPINE MILK 1.702 KG, UNPREPARED, GTIN: " +
            "07622300295127" + System.lineSeparator() +
            "Database number : 45375994" + System.lineSeparator() +
            "Full food name : MILKA, MILK CHOCOLATE CONFECTION, ALPINE MILK, " +
            "UPC: 070221103026" + System.lineSeparator() +
            "Database number : 45310024" + System.lineSeparator();

    public static final String FOOD_NOT_FOUND = "Food with name invalid is not found!" + System.lineSeparator();

    public static final String NDBNO_RESPONSE_JSON = "{\"foods\":[{\"food\":{\"sr\":\"July, 2018\",\"type\":\"b\"," +
            "\"desc\":{\"ndbno\":\"45251917\",\"name\":\"MILKA, ALPINE MILK CHOCOLATE, CHERRY CREAM, " +
            "UPC: 891128148152\",\"ds\":\"Label Insight\",\"manu\":\"Stark USA Inc.\",\"ru\":\"g\"}," +
            "\"ing\":{\"desc\":\"SUGAR, CHERRY FRUIT FILLING (20%) [GLUCOSE SYRUP, SUGAR, CHERRY PUREE (9%), " +
            "HUMECTANTS (SORBITOL), CONCENTRATED JUICE OF BLACK CARROT PUREE WITH CHOKEBERRY, ACIDITY REGULATORS " +
            "(CITRIC ACID, TARTARIC ACID), THICKENER (PECTIN), FLAVOR], VEGETABLE FAT, COCOA BUTTER, SKIMMED MILK " +
            "POWDER, WHEY POWDER, COCOA MASS, MILK FAT, CREAM POWDER (1.7%), HAZELNUT PASTE, " +
            "EMULSIFIER (SOY LECITHIN)" +
            ".\",\"upd\":\"11/02/2017\"},\"nutrients\":[{\"nutrient_id\":\"208\",\"name\":\"Energy\",\"derivation\"" +
            ":\"LCCS\",\"group\":\"Proximates\",\"unit\":\"kcal\",\"value\":\"516\",\"measures\":[{\"label\":\"GRM\"," +
            "\"eqv\":25.0,\"eunit\":\"g\",\"qty\":25.0,\"value\":\"129\"}]},{\"nutrient_id\":\"203\",\"nam" +
            "e\":\"Protein\",\"derivation\":\"LCCS\",\"group\":\"Proximates\",\"unit\":\"g\",\"valu" +
            "e\":\"4.40\",\"measures\":[{\"label\":\"GRM\",\"eqv\":25.0,\"eunit\":\"g\",\"qty\":25.0,\"va" +
            "lue\":\"1.10\"}]},{\"nutrient_id\":\"204\",\"name\":\"Total lipid (fat)\",\"derivation\":\"L" +
            "CCS\",\"group\":\"Proximates\",\"unit\":\"g\",\"value\":\"29.20\",\"measures\":[{\"label\":\"G" +
            "RM\",\"eqv\":25.0,\"eunit\":\"g\",\"qty\":25.0,\"value\":\"7.30\"}]},{\"nutrient_id\":\"205\",\"na" +
            "me\":\"Carbohydrate, by difference\",\"derivation\":\"LCCS\",\"group\":\"Proximates\",\"unit\":\"g\"" +
            ",\"value\":\"58.00\",\"measures\":[{\"label\":\"GRM\",\"eqv\":25.0,\"eunit\":\"g\",\"qty\":25.0,\"valu" +
            "e\":\"14.50\"}]},{\"nutrient_id\":\"291\",\"name\":\"Fiber, total dietary\",\"derivation\":\"LCCS\",\"gr" +
            "oup\":\"Proximates\",\"unit\":\"g\",\"value\":\"1.2\",\"measures\":[{\"label\":\"GRM\",\"eqv\":25.0,\"eu" +
            "it\":\"g\",\"qty\":25.0,\"value\":\"0.3\"}]},{\"nutrient_id\":\"269\",\"name\":\"Sugars, tot" +
            "al\",\"derivation\":\"LCCS\",\"group\":\"Proximates\",\"unit\":\"g\",\"value\":\"56.00\",\"mea" +
            "sures\":[{\"label\":\"GRM\",\"eqv\":25.0,\"eunit\":\"g\",\"qty\":25.0,\"value\":\"14.00\"}]},{\"nu" +
            "trient_id\":\"307\",\"name\":\"Sodium, Na\",\"derivation\":\"LCCS\",\"group\":\"Minerals\",\"un" +
            "it\":\"mg\",\"value\":\"1000\",\"measures\":[{\"label\":\"GRM\",\"eqv\":25.0,\"eunit\":\"g\",\"qt" +
            "y\":25.0,\"value\":\"250\"}]},{\"nutrient_id\":\"606\",\"name\":\"Fatty acids, total saturat" +
            "ed\",\"derivation\":\"LCCS\",\"group\":\"Lipids\",\"unit\":\"g\",\"value\":\"16.400\",\"measu" +
            "res\":[{\"label\":\"GRM\",\"eqv\":25.0,\"eunit\":\"g\",\"qty\":25.0,\"value\":\"4.100\"}]}],\"foo" +
            "tnotes\":[]}}],\"count\":1,\"notfound\":0,\"api\":2.0}\n";

    public static final String FOOD_REPORT_RESPONSE = "Full food name : MILKA, ALPINE MILK CHOCOLATE," +
            " CHERRY CREAM, UPC: 891128148152" + System.lineSeparator() +
            "Ingredients : SUGAR, CHERRY FRUIT FILLING (20%) [GLUCOSE SYRUP, SUGAR," +
            " CHERRY PUREE (9%), HUMECTANTS (SORBITOL), CONCENTRATED JUICE OF BLACK " +
            "CARROT PUREE WITH CHOKEBERRY, ACIDITY REGULATORS (CITRIC ACID, TARTARIC ACID)," +
            " THICKENER (PECTIN), FLAVOR], VEGETABLE FAT, COCOA BUTTER, SKIMMED MILK POWDER," +
            " WHEY POWDER, COCOA MASS, MILK FAT, CREAM POWDER (1.7%), HAZELNUT PASTE, EMULSIFIER (SOY LECITHIN)." +
            System.lineSeparator() +
            "Energy : 516 kcal" + System.lineSeparator() +
            "Protein : 4.40 g" + System.lineSeparator() +
            "Total lipid (fat) : 29.20 g" + System.lineSeparator() +
            "Carbohydrate, by difference : 58.00 g" + System.lineSeparator() +
            "Fiber, total dietary : 1.2 g" + System.lineSeparator() +
            "Sugars, total : 56.00 g" + System.lineSeparator() +
            "Sodium, Na : 1000 mg" + System.lineSeparator() +
            "Fatty acids, total saturated : 16.400 g" + System.lineSeparator();

    public static final String MILK_CHOCOLATE_NDBNO_CODE = "45157408";

    public static final String MILK_CHOCOLATE = "Full food name : CHOCOLATE CHOCOLATE CHOCOLATE, " +
            "MILK CHOCOLATE NONPAREILS, UPC: 081331001805" + System.lineSeparator() +
            "Ingredients : MILK CHOCOLATE (SUGAR, MILK, FAIR TRADE CERTIFIED COCOA BUTTER, " +
            "FAIR TRADE CERTIFIED CHOCOLATE LIQUOR, SOY LECITHIN - AN EMULSIFIER, VANILLA), VEGETABLE OIL, " +
            "SUGAR, CORN STARCH, CONFECTIONERS GLAZE" + System.lineSeparator() +
            "Energy : 550 kcal" + System.lineSeparator() +
            "Protein : 0.00 g" + System.lineSeparator() +
            "Total lipid (fat) : 32.50 g" + System.lineSeparator() +
            "Carbohydrate, by difference : 60.00 g" + System.lineSeparator() +
            "Fiber, total dietary : 2.5 g" + System.lineSeparator() +
            "Sugars, total : 55.00 g" + System.lineSeparator() +
            "Calcium, Ca : 200 mg" + System.lineSeparator() +
            "Iron, Fe : 1.80 mg" + System.lineSeparator() +
            "Sodium, Na : 75 mg" + System.lineSeparator() +
            "Vitamin C, total ascorbic acid : 0.0 mg" + System.lineSeparator() +
            "Vitamin A, IU : 0 IU" + System.lineSeparator() +
            "Fatty acids, total saturated : 17.500 g" + System.lineSeparator() +
            "Fatty acids, total trans : 0.000 g" + System.lineSeparator() +
            "Cholesterol : 12 mg";

    public static final String INVALID_GET_FOOD_REPORT_COMMAND = "1";
    public static final String FOOD_REPORT_NOT_FOUND = "Food with ndbno 1 is not found!";
    public static final String INVALID_REPORT_RESPONSE_JSON = "{\"foods\":[{\"error\":\"No data for ndbno " +
            "1\"}],\"count\":1,\"notfound\":1,\"api\":2.0}";

    public static final String HTML_RESPONSE = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/>" +
            "<meta content=\"none\" name=\"robots\"/><title>Decode Succeeded</title><link type=\"text/css\"" +
            " href=\"style.css\" rel=\"stylesheet\"/><style type=\"text/css\">\n" +
            "      td { vertical-align: top; padding: 0.1in; background-color: #EEEEEE;" +
            " font-family: monospace; }\n" +
            "    </style></head><body><div id=\"main\"><div id=\"header\"><h1><img alt=\"\" " +
            "id=\"icon\" src=\"zxing-icon.png\"/> Decode Succeeded</h1></div><table id=\"result\">" +
            "<tr><td>Raw text</td><td><pre>036000291452</pre></td></tr><tr><td>Raw bytes</td><td><pre>(Not " +
            "applicable)</pre></td></tr><tr><td>Barcode format</td><td>UPC_A</td></tr><tr><td>Parsed Result" +
            " Type</td><td>PRODUCT</td></tr><tr><td>Parsed Result</td><td><pre>036000291452</pre></td></tr></" +
            "table></div></body></html>";

    public static final String IMG_BARCODE_COMMAND = "--img=https://upload.wikimedia.org/" +
            "wikipedia/" + "commons/thumb/e/e9/" + "UPC-A-036000291452.svg/1200px-UPC-A-036000291452.svg.png";

    public static final String IMG_COMMAND = "--img=data:image/gif;base64,R0lGODlh9gBkAPc" +
            "AAAAAAAAAMwAAZgAAmQAAzAAA/wArAAArMwArZgArmQArzAAr/wBVAABVMwBVZgBVmQBVzABV/wCAAACAMwCAZgCAmQCAzAC" +
            "A/wCqAACqMwCqZgCqmQCqzACq/wDVAADVMwDVZgDVmQDVzADV/wD/AAD/MwD/ZgD/mQD/zAD//zMAADMAMzMAZjMAmTMAzDM" +
            "A/zMrADMrMzMrZjMrmTMrzDMr/zNVADNVMzNVZjNVmTNVzDNV/zOAADOAMzOAZjOAmTOAzDOA/zOqADOqMzOqZjOqmTOqzDOq/z" +
            "PVADPVMzPVZjPVmTPVzDPV/zP/ADP/MzP/ZjP/mTP/zDP//2YAAGYAM2YAZmYAmWYAzGYA/2YrAGYrM2YrZmYrmWYrzGYr/2ZVA" +
            "GZVM2ZVZmZVmWZVzGZV/2aAAGaAM2aAZmaAmWaAzGaA/2aqAGaqM2aqZmaqmWaqzGaq/2bVAGbVM2bVZmbVmWbVzGbV/2b/AGb/M2" +
            "b/Zmb/mWb/zGb//5kAAJkAM5kAZpkAmZkAzJkA/5krAJkrM5krZpkrmZkrzJkr/5lVAJlVM5lVZplVmZlVzJlV/5mAAJmAM5mAZp" +
            "mAmZmAzJmA/5mqAJmqM5mqZpmqmZmqzJmq/5nVAJnVM5nVZpnVmZnVzJnV/5n/AJn/M5n/Zpn/mZn/zJn//8wAAMwAM8wAZswAmcwA" +
            "zMwA/8wrAMwrM8wrZswrmcwrzMwr/8xVAMxVM8xVZsxVmcxVzMxV/8yAAMyAM8yAZsyAmcyAzMyA/8yqAMyqM8yqZsyqmcyqzMyq/" +
            "8zVAMzVM8zVZszVmczVzMzV/8z/AMz/M8z/Zsz/mcz/zMz///8AAP8AM/8AZv8Amf8AzP8A//8rAP8rM/8rZv8rmf8rzP8r//9VAP9" +
            "VM/9VZv9Vmf9VzP9V//+AAP+AM/+AZv+Amf+AzP+A//+qAP+qM/+qZv+qmf+qzP+q///VAP/VM//VZv/Vmf/VzP/V////AP//" +
            "M///Zv//mf//zP///wAAAAAAAAAAAAAAACH5BAEAAPwALAAAAAD2AGQAAAj/AAEIBLBvn8CCBQcORGiQIMOGBxFGTOiQYkSFEyE6V" +
            "PhQo8WNCyWG/MgQY8WGFDtm5CiS5UWTIDuSFJmSZsuTM0tirAkz5s2VOD3arDnTpM6XGYuGZJlT6EegRIc61fjy6dKgTatKZXpT5U6" +
            "UVK8eHQl2rM6zXcOanaoU6UmucL/yDAq0rtiwWr1CLcuXa1Orc432lcs369vDbu3+FeqSbFy7e5MOhuyzZ9S1c9Eu9qsWrOXJjumG1" +
            "syYcGPJp0EqTgvYJmrEsLV+lmw46mrMPyvDtG1a9F7eiWFvvuoW9+PRhUGPlb07ee3BuIc3tpq38+Wpv0sfl666u/HQca/f/+4cvPp3" +
            "3tHZku8+PTfp58DZC1ef2vB4wZkf3v9a3qfew+ixJmBr/cWXHXzKWaedb95hB15sACa3X3uzYeVggNz9x5xglL134VbEPZjhgt916Jp" +
            "oJd5VoUwfZsfZi/zJVx1+EiIXX2ttMZhjistF+FqKG8bIYnjQDUifkHj5p+BrLs5HY336hWjffDh6hqKAHMqo5HlFjthjYEhWyWSENy" +
            "4pZZTFOTghcj9iieSKMhHZ5Jga6pbll1IhuNqTZ64F5ZR45kfacUG29x9ac+qIporNmSiejQnymaaffaoJIaJXTlnoXYeeOChZ3G0";

    public static final String IMG_BARCODE_NOT_FOUND = "Food with barcode 036000291452 is not found!";
    public static final String MILK_CHOCOLATE_UPC_COMMAND = "--upc=081331001805";
    public static final String MILK_CHOCOLATE_BY_BARCODE_COMMAND = "--upc=081331001805 | " +
            "--img=https://upload.wikimedia.org/\" +\n" +
            "wikipedia/\" + \"commons/thumb/e/e9/\" + \"UPC-A-036000291452.svg/1200px-UPC-A-036000291452.svg.png";

    public static final String NEW_CLIENT_FILE_PATH = "client-database\\ImeFamiliev";

    public static final String EXISTING_NDBNO = "add-to-daily-intake 45104304 Ime Familiev";
    public static final String PRODUCT_ADDED_MESSAGE =
            "Product with ndbno 45104304 is successfully added to daily intake!";
    public static final String NEW_CLIENT_FILE = "client-database\\ImeFamiliev\\%s";
    public static final String CLIENT_CREATION = "connect localhost 4444 Ime Familiev";
    public static final String CLIENT_DIRECTORY_PATH = "client-database\\ImeFamiliev";
    public static final String NEW_NDBNO_SEARCH = "add-to-daily-intake 1 Ime Familiev";
    public static final String NDBNO = "1";
    public static final String NDBNO_ADDED = "add-to-daily-intake 45157408 Ime Familiev";
    public static final String OTHER_NDBNO_ADDED = "add-to-daily-intake 45104304 Ime Familiev";
    public static final String CLIENT_NDBNO_REMOVE = "remove-from-daily-intake 45157408 Ime Familiev";
    public static final String PRODUCT_REMOVED_MESSAGE = "Product with ndbno 45157408 is removed from daily intake!";
    public static final String CLIENT_NDBNO_NOT_FOUND = "remove-from-daily-intake 1 Ime Familiev";
    public static final String DATE_NOT_IN_DATABASE = "show-daily-intake 24-1-2019 Ime Familiev";
    public static final String DAILY_INTAKE_NOT_FOUND = "Daily intake for date 24-1-2019 is empty!";

    public static final String CLIENT_NAME = "ImeFamiliev";
    public static final String CURRENT_DAILY_INTAKE = "Your current daily intake is :" + System.lineSeparator() +
            "Energy : 981,00 kcal" + System.lineSeparator() +
            "Protein : 21,54 g" + System.lineSeparator() +
            "Fat : 63,27 g" + System.lineSeparator() +
            "Carbohydrate : 80,00 g" + System.lineSeparator() +
            "Fiber : 2,50 g" + System.lineSeparator();

    public static final String IMG_RESPONSE = "\n" +
            "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/><meta content=\"none\" name=\"robots\"/><title>" +
            "Decode " +
            "Succeeded</title><link type=\"text/css\" href=\"style.css\" rel=\"stylesheet\"/><style " +
            "type=\"text/css\">\n" +
            "      td { vertical-align: top; padding: 0.1in; background-color: #EEEEEE; font-family: monospace; }\n" +
            "    </style></head><body><div id=\"main\"><div id=\"header\"><h1><img alt=\"\" id=\"icon\" src=\"zxing-i" +
            "con.png\"/> Decode Succeeded</h1></div><table id=\"result\"><tr><td>Raw text</td><td><pre>08133100" +
            "1805</pre></td></tr><tr><td>Raw bytes</td><td><pre>69 08 0d 1f 00 12 05 2b   6a </pre></td></tr><tr>" +
            "<td>Ba" +
            "rcode format</td><td>CODE_128</td></tr><tr><td>Parsed Result Type</td><td>TEXT</td></tr><tr><td>Parsed " +
            "Result</td><td><pre>081331001805</pre></td></tr></table></div></body></html>\n";
    public static final String CONNECT_COMMAND_USER = "localhost 4444 Ime Familiev";
    public static final String CLIENT_CONNECTED_MESSAGE = "Ime Familiev, you have connected successfully!";
    public static final String CLIENT = " Ime Familiev";
}
