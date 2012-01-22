package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.*;
import by.bsu.salatmachine.model.entity.EntityIF;
import by.bsu.salatmachine.model.entity.NullEntity;
import by.bsu.salatmachine.model.logic.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 14:16
 */
public class ReceptStorageCommand implements Command {


    private static final String USER = "user";
    private static final String RECEPT_STORAGE = "receptStorage";
    private static final String VISIBILITY_PAGE_PATH = "VISIBILITY_PAGE_PATH";
    private static final String RECEPTSTORAGE_PAGE_PATH = "RECEPTSTORAGE_PAGE_PATH";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String RECEPT_ERROR_MESSAGE = "RECEPT_ERROR_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String ERROR_DATABASE_MESSAGE = "ERROR_DATABASE_MESSAGE";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        //извлечение из запроса логина и пароля
        User user = (User) request.getSession().getAttribute(USER);
        String login = user.getLogin();
        EntityIF entityIF;
        try {
            entityIF = ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT_STORAGE).getEntity(login);
            if (entityIF.getClass() != NullEntity.class) {
                TreeSetOfEntity receptStorage = (TreeSetOfEntity) entityIF;
                request.getSession().setAttribute(RECEPT_STORAGE, receptStorage);
                if (user.isType()) {
                    page = getInstance().getProperty(VISIBILITY_PAGE_PATH);
                } else {
                    page = getInstance().getProperty(RECEPTSTORAGE_PAGE_PATH);
                }
            } else {
                request.getSession().setAttribute(ERROR_MESSAGE,
                        MessageManager.getInstance()
                                .getProperty(RECEPT_ERROR_MESSAGE));
                page = getInstance()
                        .getProperty(ERROR_PAGE_PATH);
            }
        } catch (DatabaseConnectionException e) {
           String logMessage = MessageManager.getInstance()
                                .getProperty(ERROR_DATABASE_MESSAGE)+e;
            LoggerManager.getInstance().logg(LoggerManager.ERROR, logMessage);
        }

        return page;
    }
}
