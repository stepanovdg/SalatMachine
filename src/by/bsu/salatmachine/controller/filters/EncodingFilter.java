package by.bsu.salatmachine.controller.filters;

import javax.servlet.*;
import java.io.IOException;
public class EncodingFilter implements Filter {
	private String code;
	public EncodingFilter() {
    }

	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter("encoding");
	}
	public void destroy() {
		code = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    	System.out.println(" Encoding filter " + code);
		String codeRequest = request.getCharacterEncoding();
		System.out.println(" Encoding filter " + codeRequest);
		// ��������� ��������� �� ���������� �������, ���� �� �����������
        response.setContentType("text/html; charset=" + code);

		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			response.setCharacterEncoding(code);
			request.setCharacterEncoding(code);
		}	
		String value = request.getRemoteHost();
		request.setAttribute("ipAddress", value + " ������������");
		chain.doFilter(request, response);
	}
}
