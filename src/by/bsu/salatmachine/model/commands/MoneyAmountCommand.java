package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.LoggerManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.logic.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 08.01.12
 * Time: 16:06
 */
public class MoneyAmountCommand implements Command {

    private static final String USER = "user";
    private static final String LOG_MESSAGE_PART1 = "There was mistake in adding <";
    private static final String LOG_MESSAGE_PART2 = "> money. His count is ";
    private static final String MONEY_ENCRASE_AMOUNT = "moneyEncraseAmount";
    private static final String MONEY_AMOUNT_ERROR_MESSAGE = "MONEY_AMOUNT_ERROR_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String MONEY_ADD_ERROR_MESSAGE = "MONEY_ADD_ERROR_MESSAGE";
    private static final String MONEY_AMOUNT_PAGE_PATH = "MONEY_AMOUNT_PAGE_PATH";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER);
         String logMessage = LOG_MESSAGE_PART1 +user.getLogin()+
                 LOG_MESSAGE_PART2 +user.getMoney()+"";
        String money = request.getParameter(MONEY_ENCRASE_AMOUNT);
        String page = null;
        if (user == null) {
            request.getSession().setAttribute(ERROR_MESSAGE,
                    MessageManager.getInstance()
                            .getProperty(MONEY_AMOUNT_ERROR_MESSAGE));
            return getInstance()
                    .getProperty(ERROR_PAGE_PATH);
        } else {
            if (money == null) {
                return getInstance().getProperty(MONEY_AMOUNT_PAGE_PATH);
            } else {
                Integer moneyEncraseAmount = Integer.valueOf(money);
                Integer was = user.getMoney();
                user.setMoney(was + moneyEncraseAmount);
                try {
                    if (!ManagerDAO.getInstance().getDao(ManagerDAO.USER).store(user)) {
                        user.setMoney(was);
                        request.getSession().setAttribute(ERROR_MESSAGE,
                                MessageManager.getInstance().getProperty(MONEY_ADD_ERROR_MESSAGE));
                        LoggerManager.getInstance().logg(LoggerManager.ERROR,logMessage);
                    }
                    page = getInstance().getProperty(MONEY_AMOUNT_PAGE_PATH);
                } catch (DatabaseConnectionException e) {
                    LoggerManager.getInstance().logg(LoggerManager.ERROR,logMessage);
                }
            }
        }
        return page;
    }
}
