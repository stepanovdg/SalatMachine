package by.bsu.salatmachine.controller.filters;

import by.bsu.salatmachine.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;

public class SecurityFilter implements Filter {
	private static HashMap<String, EnumSet<Role>> pages = 
			new HashMap<>();
	static {
		EnumSet<Role> all = EnumSet.allOf(Role.class);
		EnumSet<Role> client = EnumSet.of(Role.CLIENT);
		pages.put("/index.jsp", all);
		pages.put("/jsp/login.jsp", all);
		// pages.put("/controller", all);
		pages.put("/jsp/main.jsp", client);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String servletPath = req.getServletPath();
		//
		// System.out.println(servletPath);
		//

		HttpSession session = req.getSession();
		Role userRole = (Role) session.getAttribute("userRole");
		//
		// System.out.println(userRole);
		//

		if (userRole == null) {
			session.setAttribute("userRole", Role.GUEST);
		}

		else if (userRole == Role.ADMIN) {
			filterChain.doFilter(req, resp);
			return;
		}

		EnumSet<Role> roles = pages.get(servletPath);
		if (roles != null && roles.contains(userRole)) {
			filterChain.doFilter(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
		// req.getRequestDispatcher("/controller").forward(req, resp);
	}
}
