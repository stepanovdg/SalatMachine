package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.model.entity.UserDAO;
import by.bsu.salatmachine.model.logic.LoginLogic;
import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:05
 */
public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        pass=passwordCrypting(pass);
        //проверка логина и пароля
        try {
           UserDAO userDAO = LoginLogic.getLogin(login, pass);
                request.getSession().setAttribute("user", userDAO);
                if (!userDAO.isType()){
                page = ConfigurationManager.getInstance()
                        .getProperty("MAIN_PAGE_PATH");
                }else {
                    page = ConfigurationManager.getInstance()
                                            .getProperty("ADMIN_PAGE_PATH");

                }



        } catch (RemoteException e) {
            request.setAttribute("errorMessage",
                        MessageManager.getInstance()
                                .getProperty("LOGIN_ERROR_MESSAGE"));
                page = ConfigurationManager.getInstance()
                        .getProperty("ERROR_PAGE_PATH");
        }
        return page;
    }
    private String passwordCrypting(String pass){
        StringBuilder passnew = new StringBuilder();
      //  if (pass.length()%2==0){
            passnew.append(String.valueOf(pass.subSequence(0,pass.length()/2).hashCode()));
            passnew.append('-');
            passnew.append(String.valueOf(pass.subSequence((pass.length() / 2) + 1, pass.length()).hashCode()));
        /*}else {
            passnew.concat(String.valueOf(pass.subSequence(0,pass.length()/2).hashCode()));
            passnew.concat("-");
            passnew.concat(String.valueOf(pass.subSequence((pass.length() / 2) + 1, pass.length()).hashCode()));
        } */

        return passnew.toString();
    }
}