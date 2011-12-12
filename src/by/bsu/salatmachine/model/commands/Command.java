package by.bsu.salatmachine.model.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:04
 *
 */
public interface Command {
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException;

}
