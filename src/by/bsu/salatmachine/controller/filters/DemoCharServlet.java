package by.bsu.salatmachine.controller.filters;

import by.bsu.salatmachine.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DemoCharServlet
 */
public class DemoCharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public DemoCharServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
	//	PrintWriter out = response.getWriter();
	
	//	out.print("���������");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		//	PrintWriter out = response.getWriter();
		
		//	out.print("���������");
			
		}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramRole = request.getParameter("role");
		//
        System.out.println(paramRole);
        //
		String jsp = "/jsp/login.jsp";
//	if (paramRole == null){
//		jsp="/jsp/login.jsp";		
//	}
		
//	else 
		if(paramRole != null && (paramRole.equalsIgnoreCase("admin")||paramRole.equalsIgnoreCase("client"))) {
		//
        System.out.println(paramRole);
        //
			Role current = Role.valueOf(paramRole.toUpperCase());
			switch(current){
			case ADMIN: 
				request.getSession().setAttribute("userRole", Role.ADMIN);
				jsp="/jsp/admin.jsp";break;
			case CLIENT: 
				request.getSession().setAttribute("userRole", Role.CLIENT);
				jsp="/jsp/main.jsp";break;
		
			}
		}
	request.getRequestDispatcher(jsp).forward(request, response);


}
}