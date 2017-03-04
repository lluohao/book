package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.ICollectionService;
import com.code.service.fact.ServiceFactory;

public class RemoveCollectionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String bookIdStr = req.getParameter("bookId");
		int userId = (Integer) req.getSession().getAttribute("userId");
		int bookId = 0;
		try {
			bookId = Integer.parseInt(bookIdStr);
		} catch (Exception e) {
		}
		ICollectionService service = ServiceFactory.getInstance()
				.newCollectionService();
		boolean collectId = service.removeCollection(bookId, userId);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(collectId);
	}
}
