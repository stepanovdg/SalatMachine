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
    private static final String BUNDLE_NAME = "resources." + "config";


    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle =
                    ResourceBundle.getBundle(BUNDLE_NAME, new Locale("",""));
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
