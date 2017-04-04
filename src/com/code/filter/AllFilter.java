package com.code.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AllFilter implements Filter {
	private boolean printError = false;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) res;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		try {
			chain.doFilter(req, resp);
		} catch (Exception e) {
			if (!resp.isCommitted()) {
				resp.sendRedirect("500.jsp");
			}
			if (printError) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		printError = "true".equals(config.getInitParameter("printError")
				.toLowerCase());
	}

}
