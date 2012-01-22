package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.ReceptStorage;
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
 * Date: 29.12.11
 * Time: 15:55
 */
public class NewReceptStCommand implements Command {

    private static final String RECEPT_ST_NAME = "receptStName";
    private static final String USER = "user";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String RECEPTSTORAGE_ERROR_CREATE_MESSAGE = "RECEPTSTORAGE_ERROR_CREATE_MESSAGE";
    private static final String RECEPTSTORAGE_PAGE_PATH = "RECEPTSTORAGE_PAGE_PATH";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String name = request.getParameter(RECEPT_ST_NAME);
        String visibility = ((User) request.getSession().getAttribute(USER)).getLogin();
        ReceptStorage receptst = new ReceptStorage(0, name, visibility);
        try {
            ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT_STORAGE).create(receptst);
        } catch (DatabaseConnectionException e) {
            request.setAttribute(ERROR_MESSAGE,
                    getInstance()
                            .getProperty(RECEPTSTORAGE_ERROR_CREATE_MESSAGE));
            if (e.isCorrectable()) {
                return ConfigurationManager.getInstance()
                        .getProperty(RECEPTSTORAGE_PAGE_PATH);
            } else {
                return ConfigurationManager.getInstance()
                        .getProperty(ERROR_PAGE_PATH);
            }
        }
        ReceptStorageCommand receptStorageCommand= new ReceptStorageCommand();
        page = receptStorageCommand.execute(request,response);
        return page;

    }
}
