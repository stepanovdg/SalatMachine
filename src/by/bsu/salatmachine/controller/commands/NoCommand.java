package by.bsu.salatmachine.controller.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:07
 */
public class NoCommand implements Command {
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
/*в случае прямого обращения к контроллеру переадресация на страницу ввода
логина*/
        return getInstance()
                .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
    }
}