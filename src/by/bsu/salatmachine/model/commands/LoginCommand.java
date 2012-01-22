package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.EntityIF;
import by.bsu.salatmachine.model.entity.NullEntity;
import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.logic.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.MessageManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:05
 */
public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String USER = "user";
    private static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    private static final String ADMIN_PAGE_PATH = "ADMIN_PAGE_PATH";
    private static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final int PASSWORD_MAXIMUM_LENGTH = 32;
    private static final int LOGIN_MAXIMUM_LENGTH = 16;

    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String errorMessage;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (!validateLogin(login) || !validatePass(pass)) {
            errorMessage = getInstance().getProperty(LOGIN_ERROR_MESSAGE);
            request.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
            return ConfigurationManager.getInstance()
                    .getProperty(LOGIN_PAGE_PATH);

        }
        pass = passwordCrypting(pass);
        //проверка логина и пароля
        EntityIF entityIF;
        try {
            entityIF = ManagerDAO.getInstance().getDao(ManagerDAO.USER).getEntity(login, pass);
            if (entityIF.getClass() != NullEntity.class) {
                User user = (User) entityIF;
                request.getSession().setAttribute(USER, user);
                if (!user.isType()) {
                    page = ConfigurationManager.getInstance()
                            .getProperty(MAIN_PAGE_PATH);
                } else {
                    page = ConfigurationManager.getInstance()
                            .getProperty(ADMIN_PAGE_PATH);
                }
            } else {
                request.setAttribute(ERROR_MESSAGE,
                        getInstance()
                                .getProperty(LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance()
                        .getProperty(ERROR_PAGE_PATH);
            }
        } catch (DatabaseConnectionException e) {
            errorMessage = getInstance().getProperty(LOGIN_ERROR_MESSAGE);
            request.setAttribute(ERROR_MESSAGE, errorMessage);
            LoggerManager.getInstance().logg(LoggerManager.ERROR, errorMessage);
            if (e.isCorrectable()) {
                page = ConfigurationManager.getInstance()
                        .getProperty(LOGIN_PAGE_PATH);
            } else {
                page = ConfigurationManager.getInstance()
                        .getProperty(ERROR_PAGE_PATH);
            }

        }

        return page;
    }

    private boolean validateLogin(String login) {
        if (login.equals(""))
            return false;
        if (login.length() > LOGIN_MAXIMUM_LENGTH)
            return false;
        return true;
    }

    private boolean validatePass(String pass) {
        if (pass.equals(""))
            return false;
        if (pass.length() > PASSWORD_MAXIMUM_LENGTH)
            return false;

        return true;
    }

    private String passwordCrypting(String pass) {
        StringBuilder passnew = new StringBuilder();
        passnew.append(String.valueOf(pass.subSequence(0, pass.length() / 2).hashCode()));
        passnew.append('-');
        passnew.append(String.valueOf(pass.subSequence((pass.length() / 2) + 1, pass.length()).hashCode()));
        return passnew.toString();
    }
}