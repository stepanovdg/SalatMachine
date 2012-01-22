package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 20.12.11
 * Time: 17:37
 */
public class RefreshCommand implements Command {

    private static final String PAGE = "page";
    private static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    private static final String ADD_RECEPT = "addRecept";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter(PAGE);
        Boolean add;
       if (page == null) {
            return ConfigurationManager.getInstance().getProperty(LOGIN_PAGE_PATH);
        } else {
            add = Boolean.valueOf(request.getParameter(ADD_RECEPT));
            if (add != null) {
                request.getSession().setAttribute(ADD_RECEPT, add);
            }
            return page;
        }
    }
}

