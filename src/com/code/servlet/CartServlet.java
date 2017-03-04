package com.code.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			//Integer userId=(Integer)req.getSession().getAttribute("userId");
		} catch (Exception e) {
			throw new RuntimeException("CartServlet");
		}
		
		
//		//测试数据
//		req.getSession().setAttribute("userId", 100001);
		
		
		
		
		req.getRequestDispatcher("myCart.jsp").forward(req, resp);
	}
}
