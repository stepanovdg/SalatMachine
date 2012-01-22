package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.logic.ManagerDAO;
import by.bsu.salatmachine.model.logic.ReceptStorageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 08.01.12
 * Time: 19:32
 */
public class VisibilityChangeCommand implements Command {


    private static final String ERROR_DATABASE_MESSAGE = "ERROR_DATABASE_MESSAGE";
    private static final String VISIBILITY_CHANGE_ERROR_MESSAGE = "VISIBILITY_CHANGE_ERROR_MESSAGE";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String VISIBILITY_ERROR_MESSAGE = "VISIBILITY_ERROR_MESSAGE";
    private static final String ID_RECEPT = "idRecept";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String idRecept = request.getParameter(ID_RECEPT);
        if (idRecept == null) {
            request.getSession().setAttribute(ERROR_MESSAGE,
                    MessageManager.getInstance()
                            .getProperty(VISIBILITY_ERROR_MESSAGE));
            return getInstance()
                    .getProperty(ERROR_PAGE_PATH);
        } else {
            Integer rec = Integer.valueOf(idRecept);
            try {
                if (!((ReceptStorageDAO) ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT_STORAGE)).visibilityChange(rec)) {
                    request.getSession().setAttribute(ERROR_MESSAGE,
                            MessageManager.getInstance().getProperty(VISIBILITY_CHANGE_ERROR_MESSAGE));
                }
                ReceptStorageCommand receptStorageCommand = new ReceptStorageCommand();
                page = receptStorageCommand.execute(request, response);
            } catch (DatabaseConnectionException e) {
               String logMessage = MessageManager.getInstance()
                                .getProperty(ERROR_DATABASE_MESSAGE)+e;
                LoggerManager.getInstance().logg(LoggerManager.ERROR, logMessage);
            }
        }
        return page;
    }
}
