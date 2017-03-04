package com.code.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpSession sessions = hreq.getSession();
		Integer id = (Integer) sessions.getAttribute("userId");
		if(id==null||id<0){
			hreq.setAttribute("mesg", "请先登录");
			hreq.setAttribute("preURL", hreq.getRequestURI());
			hreq.getRequestDispatcher("/login.jsp").forward(hreq, res);
		}else{
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
