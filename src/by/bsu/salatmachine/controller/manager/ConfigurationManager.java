package by.bsu.salatmachine.controller.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:08
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    //класс извлекает информацию из файла config.properties
    private static final String BUNDLE_NAME = "by.bsu.salatmachine.controller.manager." + "config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String DATABASE_URL_PREFIX = "DATABASE_URL_PREFIX";
    public static final String DATABASE_CONNECTION = "DATABASE_CONNECTION";
    public static final String DATABASE_USER = "DATABASE_USER";
    public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle =
                    ResourceBundle.getBundle(BUNDLE_NAME, new Locale("ru", "RU"));
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
