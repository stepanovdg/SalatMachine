package by.bsu.salatmachine.controller.manager;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:09
 *
 */
public class MessageManager {
    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    //класс извлекает информацию из файла messages. properties
    private static final String BUNDLE_NAME = "resources.messages";


    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
            instance.resourceBundle =
                    ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
