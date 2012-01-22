package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:07
 */
public class NoCommand implements Command {

    private static final String USER = "user";
    private static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    private static final String ADMIN_PAGE_PATH = "ADMIN_PAGE_PATH";
    private static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";

    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER);
        if (user == null) {
            return getInstance()
                    .getProperty(LOGIN_PAGE_PATH);
        } else {
            if (user.isType()){
                return getInstance().getProperty(ADMIN_PAGE_PATH);
            }else {
                return getInstance().getProperty(MAIN_PAGE_PATH);
            }
        }
    }
}