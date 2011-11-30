package by.bsu.salatmachine.controller;

import by.bsu.salatmachine.controller.commands.Command;
import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;

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

public class ControllerServlet extends HttpServlet {
    RequestHelper requestHelper = RequestHelper.getInstance();

    public ControllerServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        /* RequestDispatcher dispatcher = null;
      String param = request.getParameter("go");

      if (param == null) {
          throw new ServerException("Параметр не задан");
      } else if (param.equals("weather")) {
          dispatcher = request.getRequestDispatcher("/weather");
          //  dispatcher = getServletContext().getNamedDispatcher("Weather");
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
      }
      /*response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");

      PrintWriter out = response.getWriter();
      out.println("<html><head>");
      out.println("<h2>URL:this is i </h2>");
      response.sendRedirect(response.encodeRedirectURL("/index.jsp"));
     /* out.println("<title>Отображение на сервлет всех запросов к web-приложению</title></head><body>");
      out.println("<h2>URL запроса:");

      out.println(request.getPathInfo() + "</h2>");

      out.println("</body></html>");  */
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        try {
//определение команды, пришедшей из JSP
            Command command = requestHelper.getCommand(request);
            request.setCharacterEncoding("UTF-8");
/*вызов реализованного метода execute() интерфейса Command и передача
параметров классу-обработчику конкретной команды*/
            page = command.execute(request, response);
// метод возвращает страницу ответа
        } catch (ServletException e) {
            e.printStackTrace();
//генерация сообщения об ошибке
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
//вызов JSP-страницы c cообщением об ошибке
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
//вызов страницы ответа на запрос
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /* private void doProcess(HttpServletRequest request,
                         HttpServletResponse response)
          throws IOException, ServletException {

      try {
          getRequestProcessor().processRequest(request);
          getScreenFlowManager().forwardToNextScreen(request, response);
      } catch (Throwable ex) {
          String className = ex.getClass().getName();
          nextScreen = getScreenFlowManager().getExceptionScreen(ex);
          // put the exception in the request
          request.setAttribute("javax.servlet.jsp.jspException", ex);
          if (nextScreen == null) {
              // send to general error screen
              ex.printStackTrace();
              throw new ServletException("MainServlet: unknown exception: " +
                      className);
          }
      }
  }  */

}
