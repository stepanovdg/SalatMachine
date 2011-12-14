package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.model.entity.ReceptStorage;
import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.logic.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

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
          ReceptStorage receptStorage = null;
        //извлечение из запроса логина и пароля
        User user = (User) request.getSession().getAttribute("user");
        String login = user.getLogin();

        //проверка логина и пароля
        try {
            if(login!=null){
                receptStorage = (ReceptStorage) ManagerDAO.getInstance().getDao("receptStorage").getEntity(login);
            } else{
                page = getInstance().getProperty("LOGIN_PAGE_PATH");
            }
                request.getSession().setAttribute("receptStorage", receptStorage);
                page = getInstance().getProperty("RECEPTSTORAGE_PAGE_PATH");

        } catch (RemoteException e) {
            request.setAttribute("errorMessage",
                        MessageManager.getInstance()
                                .getProperty("RECEPT_ERROR_MESSAGE"));
                page = getInstance()
                        .getProperty("ERROR_PAGE_PATH");
        }
        return page;
    }
}
