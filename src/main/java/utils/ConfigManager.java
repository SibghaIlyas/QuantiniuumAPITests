package utils;

public class ConfigManager {

    public static String getBaseURI() {
        String envBaseUrl = System.getProperty("baseUrl");
        return (envBaseUrl != null) ? envBaseUrl : "https://restful-booker.herokuapp.com"; //default url
    }
}
