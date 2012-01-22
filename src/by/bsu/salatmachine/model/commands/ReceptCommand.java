package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.enums.VegetEnum;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.*;
import by.bsu.salatmachine.model.logic.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 9:12
 */
public class ReceptCommand implements Command {

    private static final String USER = "user";
    private static final String ID_RECEPT = "idRecept";
    private static final String RECEPT_STORAGE = "receptStorage";
    private static final String MODABLE = "modable";
    private static final String CHOOSE = "choose";
    private static final String RECEPT = "recept";
    private static final String RECEPT_PAGE_PATH = "RECEPT_PAGE_PATH";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String RECEPT_ERROR_MESSAGE = "RECEPT_ERROR_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String ERROR_DATABASE_MESSAGE = "ERROR_DATABASE_MESSAGE";
    private static final String COOK_PROMISE = "cookPromise";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        User user = (User) request.getSession().getAttribute(USER);
        Integer idRecept = Integer.valueOf(request.getParameter(ID_RECEPT));
        TreeSetOfEntity receptStorage  = (TreeSetOfEntity) request.getSession().getAttribute(RECEPT_STORAGE);
        String visibility = "";
        ReceptStorage receptst;
        for (Object rec : receptStorage){
            receptst = (ReceptStorage) rec;
             if (receptst.getIdRecept().equals(idRecept)){
                visibility = receptst.getVisibility();
             }
        }
        if (user.getLogin().toLowerCase().equals(visibility.toLowerCase())){
            request.getSession().setAttribute(MODABLE,true);
        }else{
            request.getSession().setAttribute(MODABLE,false);
        }
        EntityIF entityIF;
        try {
            entityIF = ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT).getEntity(idRecept);
            if (entityIF.getClass() != NullEntity.class) {
                TreeSetOfEntity recept = (TreeSetOfEntity) entityIF;
                request.getSession().setAttribute(RECEPT, recept);
                page = ConfigurationManager.getInstance().getProperty(RECEPT_PAGE_PATH);
            } else {
                Integer veget = new Random().nextInt(VegetEnum.values().length);
                Recept rec = new Recept(idRecept, 1, veget, 0.1, CHOOSE, 0);
                if (ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT).create(rec)) {
                    entityIF = ManagerDAO.getInstance().getDao(ManagerDAO.RECEPT).getEntity(idRecept);
                    TreeSetOfEntity recept = (TreeSetOfEntity) entityIF;
                    request.getSession().setAttribute(RECEPT, recept);
                    page = ConfigurationManager.getInstance().getProperty(RECEPT_PAGE_PATH);
                } else {

                    request.getSession().setAttribute(ERROR_MESSAGE,
                            MessageManager.getInstance()
                                    .getProperty(RECEPT_ERROR_MESSAGE));
                    page = ConfigurationManager.getInstance()
                            .getProperty(ERROR_PAGE_PATH);
                }
            }
        } catch (DatabaseConnectionException e) {
           String logMessage = MessageManager.getInstance()
                                .getProperty(ERROR_DATABASE_MESSAGE)+e;
            LoggerManager.getInstance().logg(LoggerManager.ERROR, logMessage);
        }
        request.getSession().setAttribute(COOK_PROMISE, true);
        return page;
    }
}
