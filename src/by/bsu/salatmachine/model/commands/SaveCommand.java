package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.*;
import by.bsu.salatmachine.model.logic.ManagerDAO;
import by.bsu.salatmachine.model.logic.ReceptDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.MessageManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 18.12.11
 * Time: 16:36
 */
public class SaveCommand implements Command {

    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String RECEPT = "recept";
    private static final String MODABLE = "modable";
    private static final String RECEPT_ERROR_DELETE_MESSAGE = "RECEPT_ERROR_DELETE_MESSAGE";
    private static final String RECEPT_ERROR_CREATE_MESSAGE = "RECEPT_ERROR_CREATE_MESSAGE";
    private static final String RECEPT_PAGE_PATH = "RECEPT_PAGE_PATH";
    private static final String ERROR_SAVING_CHANGES_MESSAGE = "ERROR_SAVING_CHANGES_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        TreeSetOfEntity nowSetOfEntity = (TreeSetOfEntity) request.getSession().getAttribute(RECEPT);
        Boolean modable = (Boolean) request.getSession().getAttribute(MODABLE);
        if (modable) {
            try {
                if (((ReceptDAO) ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT))
                        .delete(((Recept) nowSetOfEntity.toArray()[0]).getIdRecept())) {
                } else throw new DatabaseConnectionException();
            } catch (DatabaseConnectionException e) {
                request.getSession().setAttribute(ERROR_MESSAGE,
                        MessageManager.getInstance()
                                .getProperty(RECEPT_ERROR_DELETE_MESSAGE));
                return ConfigurationManager.getInstance()
                        .getProperty(ERROR_PAGE_PATH);
            }
            try {
                if (ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT).create(nowSetOfEntity)) {
                    page = ConfigurationManager.getInstance()
                            .getProperty(RECEPT_PAGE_PATH);
                } else throw new DatabaseConnectionException();
            } catch (DatabaseConnectionException e) {
                request.setAttribute(ERROR_MESSAGE,
                        getInstance()
                                .getProperty(RECEPT_ERROR_CREATE_MESSAGE));
                if (e.isCorrectable()) {
                    page = ConfigurationManager.getInstance()
                            .getProperty(RECEPT_PAGE_PATH);
                } else {
                    page = ConfigurationManager.getInstance()
                            .getProperty(ERROR_PAGE_PATH);
                }
            }
        } else {
             request.getSession().setAttribute(ERROR_MESSAGE,
                        MessageManager.getInstance()
                                .getProperty(ERROR_SAVING_CHANGES_MESSAGE));
                return ConfigurationManager.getInstance()
                        .getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}