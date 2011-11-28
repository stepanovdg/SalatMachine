package by.bsu.salatmachine.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 18:39
 *
 */
@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
       /* String param = request.getParameter("go");

        if (param == null) {
            throw new ServerException("Параметр не задан");
        } else if (param.equals("weather")) {
            // dispatcher = request.getRequestDispatcher("/weather");
            dispatcher = getServletContext().getNamedDispatcher("Weather");
        } else if (param.equals("maps")) {
            // dispatcher = request.getRequestDispatcher("/maps");
            dispatcher = getServletContext().getNamedDispatcher("Maps");
        } else {
            throw new ServerException("Неправильный параметр");
        }

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            throw new ServerException("Dispather is NULL");
        }*/
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<h2>URL:this is i </h2>");
        //response.sendRedirect(response.encodeRedirectURL("/index.jsp"));
       /* out.println("<title>Отображение на сервлет всех запросов к web-приложению</title></head><body>");
        out.println("<h2>URL запроса:");

        out.println(request.getPathInfo() + "</h2>"); */

        out.println("</body></html>");
    }
}
