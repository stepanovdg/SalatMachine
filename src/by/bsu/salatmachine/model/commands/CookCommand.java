package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.*;
import by.bsu.salatmachine.model.logic.ManagerDAO;
import by.bsu.salatmachine.model.logic.VegetableDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 19.12.11
 * Time: 12:14
 */
public class CookCommand implements Command {

    private static final String RECEPT = "recept";
    private static final String USER = "user";
    private static final String LOG_MESSAGE_PART1 = "There was mistake in storing <";
    private static final String LOG_MESSAGE_PART2 = "> money. His count is ";
    private static final String PRICE = "price";
    private static final String COOK_PAGE_PATH = "COOK_PAGE_PATH";
    private static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    private static final String COOK_MONEY_ERROR_MESSAGE = "COOK_MONEY_ERROR_MESSAGE";
    private static final String RECEPT_PAGE_PATH = "RECEPT_PAGE_PATH";
    private static final String COOK_ERROR_MESSAGE = "COOK_ERROR_MESSAGE";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String COOK_ERROR_MONEY_MESSAGE = "COOK_ERROR_MONEY_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String COOK_PROMISE = "cookPromise";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        TreeSetOfEntity receptSet = (TreeSetOfEntity) request.getSession().getAttribute(RECEPT);
        User user = (User) request.getSession().getAttribute(USER);
        String logMessage = LOG_MESSAGE_PART1 +user.getLogin()+
                LOG_MESSAGE_PART2 +user.getMoney()+"";
        String price = request.getParameter(PRICE);
        price = price.substring(0, price.length() - 1);
        Integer recPrice = Double.valueOf(price).intValue();
        TreeSetOfEntity<Vegetable> vegetst = new TreeSetOfEntity<>();
        EntityIF entityIF;
        Iterator it = receptSet.iterator();
        Recept recept;
        try {
            Boolean promise = (Boolean) request.getSession().getAttribute(COOK_PROMISE);
            if (promise) {
                page = ConfigurationManager.getInstance().getProperty(COOK_PAGE_PATH);
            } else {
                return ConfigurationManager.getInstance().getProperty(MAIN_PAGE_PATH);
            }
            Integer ostatok = user.getMoney() - recPrice;
            if (ostatok < 0) {
                request.getSession().setAttribute(ERROR_MESSAGE,
                        MessageManager.getInstance()
                                .getProperty(COOK_MONEY_ERROR_MESSAGE));
                return ConfigurationManager.getInstance()
                        .getProperty(RECEPT_PAGE_PATH);
            } else {
                user.setMoney(ostatok);
            }
            while (it.hasNext()) {
                recept = (Recept) it.next();
                if (recept.getProcess() == VegetMethodOfPreparationEnum.CHOOSE) {
                    double count = 0;
                    while (recept.getCount() > count) {
                        entityIF = ManagerDAO.getInstance().getDao(ManagerDAO.VEGETABLE).getEntity(VegetableDAO.IDVEGETENUM, recept.getIdVegetEnum());
                        if (entityIF.getClass() != NullEntity.class) {
                            vegetst.add(entityIF);
                            count += ((Vegetable) entityIF).getWeightPerKg();
                        } else {
                            user.setMoney(ostatok + recPrice);
                            request.getSession().setAttribute(ERROR_MESSAGE,
                                    MessageManager.getInstance()
                                            .getProperty(COOK_ERROR_MESSAGE));
                            return ConfigurationManager.getInstance()
                                    .getProperty(ERROR_PAGE_PATH);
                        }
                    }
                }
            }
            if (!ManagerDAO.getInstance().getDao(ManagerDAO.USER).store(user)) {
                LoggerManager.getInstance().logg(LoggerManager.ERROR,logMessage);
                  request.getSession().setAttribute(ERROR_MESSAGE,
                                    MessageManager.getInstance()
                                            .getProperty(COOK_ERROR_MONEY_MESSAGE));
                            return ConfigurationManager.getInstance()
                                    .getProperty(ERROR_PAGE_PATH);

            }
            request.getSession().setAttribute(COOK_PROMISE, false);
        } catch (DatabaseConnectionException e) {
                LoggerManager.getInstance().logg(LoggerManager.ERROR,logMessage);
        }
        return page;
    }
}
