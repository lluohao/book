package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class SimpleBookServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		IBookService service = ServiceFactory.getInstance().newBookService();
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		out.print(service.simpleText(bookId == null ? 0 : bookId,"GB2312"));
	}
}
