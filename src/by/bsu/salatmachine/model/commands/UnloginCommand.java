package by.bsu.salatmachine.model.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 15.12.11
 * Time: 18:02
 */
public class UnloginCommand implements Command {


    private static final String USER = "user";
    private static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(USER);
        request.getSession().invalidate();
        return getInstance().getProperty(LOGIN_PAGE_PATH);
    }
}

