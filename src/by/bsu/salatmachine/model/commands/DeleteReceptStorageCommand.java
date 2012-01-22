package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.ReceptStorage;
import by.bsu.salatmachine.model.entity.TreeSetOfEntity;
import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.logic.ManagerDAO;
import by.bsu.salatmachine.model.logic.ReceptDAO;
import by.bsu.salatmachine.model.logic.ReceptStorageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 29.12.11
 * Time: 20:06
 */
public class DeleteReceptStorageCommand implements Command {


    private static final String USER = "user";
    private static final String ID_RECEPT = "idRecept";
    private static final String RECEPT_STORAGE = "receptStorage";
    private static final String RECEPTSTORAGE_ERROR_DELETE_MESSAGE = "RECEPTSTORAGE_ERROR_DELETE_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_DELETING_RECEPTSTORAGE_MESSAGE = "ERROR_DELETING_RECEPTSTORAGE_MESSAGE";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        User user = (User) request.getSession().getAttribute(USER);
        Integer idRecept = Integer.valueOf(request.getParameter(ID_RECEPT));
        TreeSetOfEntity receptStorage = (TreeSetOfEntity) request.getSession().getAttribute(RECEPT_STORAGE);
        String visibility = "";
        ReceptStorage recept;
        for (Object rec : receptStorage) {
            recept = (ReceptStorage) rec;
            if (recept.getIdRecept().equals(idRecept)) {
                visibility = recept.getVisibility();
            }
        }
        if (user.isType() || (user.getLogin().toLowerCase().equals(visibility.toLowerCase()))) {
            try {
                ((ReceptStorageDAO) ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT_STORAGE)).delete(idRecept);
                ((ReceptDAO) ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT)).delete(idRecept);
            } catch (DatabaseConnectionException e) {
                String errorMessage =  MessageManager.getInstance()
                                .getProperty(RECEPTSTORAGE_ERROR_DELETE_MESSAGE);
                request.getSession().setAttribute(ERROR_MESSAGE,errorMessage);
                LoggerManager.getInstance().logg(LoggerManager.ERROR,errorMessage);
                return ConfigurationManager.getInstance()
                        .getProperty(ERROR_PAGE_PATH);
            }
        }else {
             request.getSession().setAttribute(ERROR_MESSAGE,
                        MessageManager.getInstance()
                                .getProperty(ERROR_DELETING_RECEPTSTORAGE_MESSAGE));
        }
        ReceptStorageCommand receptStorageCommand = new ReceptStorageCommand();
        page = receptStorageCommand.execute(request, response);
        return page;
    }
}
