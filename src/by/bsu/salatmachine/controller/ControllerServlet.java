package by.bsu.salatmachine.controller;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.model.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 18:39
 */

public final class ControllerServlet extends HttpServlet {


    private static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";

    public ControllerServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        try {
    //определение команды, пришедшей из JSP
            Command command = RequestHelper.getInstance().getCommand(request);
    /*вызов реализованного метода execute() интерфейса Command и передача
    параметров классу-обработчику конкретной команды*/
            page = command.execute(request, response);
    // метод возвращает страницу ответа
        } catch (ServletException e) {
    //генерация сообщения об ошибке
            request.getSession().setAttribute(ERROR_MESSAGE,
                    MessageManager.getInstance().getProperty(SERVLET_EXCEPTION_ERROR_MESSAGE));
    //вызов JSP-страницы c cообщением об ошибке
            page = ConfigurationManager.getInstance().getProperty(ERROR_PAGE_PATH);
        } catch (IOException e) {
            request.getSession().setAttribute(ERROR_MESSAGE,
                    MessageManager.getInstance().getProperty(IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance()
                    .getProperty(ERROR_PAGE_PATH);
        }

    //вызов страницы ответа на запрос
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
