package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.model.entity.Recept;
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
 * Date: 13.12.11
 * Time: 9:12
 */
public class ReceptCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
          Recept recept = null;
        //извлечение из запроса логина и пароля
        User user = (User) request.getSession().getAttribute("user");
        String login = user.getLogin();
        Integer idRecept = (Integer) request.getAttribute("idRecept");

        //проверка логина и пароля
        try {
            if(login!=null){
                recept = (Recept) ManagerDAO.getInstance().getDao("recept").getEntity(idRecept);
            }else{
                page = getInstance().getProperty("LOGIN_PAGE_PATH");
            }
                request.getSession().setAttribute("recept", recept);
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
