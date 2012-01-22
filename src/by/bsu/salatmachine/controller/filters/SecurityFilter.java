package by.bsu.salatmachine.controller.filters;

import by.bsu.salatmachine.enums.Role;
import by.bsu.salatmachine.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;

import static by.bsu.salatmachine.controller.manager.ConfigurationManager.getInstance;

public class SecurityFilter implements Filter {
	private static HashMap<String, EnumSet<Role>> pages = 
			new HashMap<>();


    private static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    private static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    private static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    private static final String MONEY_AMOUNT_PAGE_PATH = "MONEY_AMOUNT_PAGE_PATH";
    private static final String COOK_PAGE_PATH = "COOK_PAGE_PATH";
    private static final String ADMIN_PAGE_PATH = "ADMIN_PAGE_PATH";
    private static final String VISIBILITY_PAGE_PATH = "VISIBILITY_PAGE_PATH";
    private static final String RECEPT_PAGE_PATH = "RECEPT_PAGE_PATH";
    private static final String RECEPTSTORAGE_PAGE_PATH = "RECEPTSTORAGE_PAGE_PATH";
    private static final String USER = "user";
    private static final String INDEX_JSP = "/index.jsp";

    static {
		EnumSet<Role> all = EnumSet.allOf(Role.class);
		EnumSet<Role> client = EnumSet.of(Role.CLIENT);
        EnumSet<Role> admin = EnumSet.of(Role.ADMIN);
        EnumSet<Role> clAd = EnumSet.of(Role.ADMIN,Role.CLIENT);
		pages.put(INDEX_JSP, all);
		pages.put(getInstance().getProperty(LOGIN_PAGE_PATH), all);
		pages.put(getInstance().getProperty(ERROR_PAGE_PATH), all);
		pages.put(getInstance().getProperty(MAIN_PAGE_PATH), client);
		pages.put(getInstance().getProperty(MONEY_AMOUNT_PAGE_PATH), client);
		pages.put(getInstance().getProperty(COOK_PAGE_PATH), client);
		pages.put(getInstance().getProperty(ADMIN_PAGE_PATH), admin);
		pages.put(getInstance().getProperty(VISIBILITY_PAGE_PATH), admin);
		pages.put(getInstance().getProperty(RECEPT_PAGE_PATH), clAd);
		pages.put(getInstance().getProperty(RECEPTSTORAGE_PAGE_PATH), clAd);
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
		HttpSession session = req.getSession();
		Role userRole = Role.getValue((User) session.getAttribute(USER));
		EnumSet<Role> roles = pages.get(servletPath);
		if (roles != null && roles.contains(userRole)) {
			filterChain.doFilter(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + INDEX_JSP);
	}
}
