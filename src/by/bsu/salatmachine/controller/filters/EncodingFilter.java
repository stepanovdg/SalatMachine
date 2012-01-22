package by.bsu.salatmachine.controller.filters;

import javax.servlet.*;
import java.io.IOException;
public class EncodingFilter implements Filter {
    private static final String ENCODING = "encoding";
    private static final String CONTENT_TYPE = "text/html; charset=";
    private static final String IP_ADDRESS = "ipAddress";
    private String code;
	public EncodingFilter() {
    }

	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter(ENCODING);
		//code = "UTF-8";
	}
	public void destroy() {
		code = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();
        response.setContentType(CONTENT_TYPE + code);
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			response.setCharacterEncoding(code);
			request.setCharacterEncoding(code);
		}	
		String value = request.getRemoteHost();
		request.setAttribute(IP_ADDRESS, value);
		chain.doFilter(request, response);
	}
}
