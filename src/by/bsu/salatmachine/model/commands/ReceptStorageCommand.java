package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.model.entity.ReceptStorageDAO;
import by.bsu.salatmachine.model.entity.UserDAO;
import by.bsu.salatmachine.model.logic.ReceptStorageLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 14:16
 *
 */
public class ReceptStorageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String page = null;
          ReceptStorageDAO  receptStorageDAO;
        //извлечение из запроса логина и пароля
        UserDAO user = (UserDAO) request.getSession().getAttribute("user");
        String login = user.getLogin();

        //проверка логина и пароля
        try {
            if(login!=null){
                receptStorageDAO = ReceptStorageLogic.getRecept(login);
            }else {
                receptStorageDAO = ReceptStorageLogic.getRecept("admin");
            }
                request.getSession().setAttribute("receptstorage",receptStorageDAO);
                page = ConfigurationManager.getInstance().getProperty("RECEPT_PAGE_PATH");

        } catch (RemoteException e) {
            request.setAttribute("errorMessage",
                        MessageManager.getInstance()
                                .getProperty("RECEPT_ERROR_MESSAGE"));
                page = ConfigurationManager.getInstance()
                        .getProperty("ERROR_PAGE_PATH");
        }
        return page;
    }
}
