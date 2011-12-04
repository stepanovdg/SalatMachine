package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.logic.LoginLogic;
import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:05
 */
public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        //проверка логина и пароля
        try {
           User user = LoginLogic.getLogin(login, pass);
                request.setAttribute("user",user);

                page = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.MAIN_PAGE_PATH);




        } catch (RemoteException e) {
            request.setAttribute("errorMessage",
                        MessageManager.getInstance()
                                .getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}