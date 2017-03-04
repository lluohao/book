package com.code.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharSetFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new MyServletRequest((HttpServletRequest) req), res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	private class MyServletRequest extends HttpServletRequestWrapper {

		public MyServletRequest(HttpServletRequest request) {
			super(request);
		}
		@Override
		public String getParameter(String name) {
			if (super.getParameter(name) != null) {
				try {
					return new String(super.getParameter(name).getBytes(
							"UTF-8"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return super.getParameter(name);
		}
	}

}
