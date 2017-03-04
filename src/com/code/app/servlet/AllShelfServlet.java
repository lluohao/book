package com.code.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class AllShelfServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		IBookService service = ServiceFactory.getInstance().newBookService();
		int userId = Integer.parseInt(req.getParameter("userId"));
		List<Book> myBooks = service.userBooks(userId);
		JSONArray arr = new JSONArray();
		arr.addAll(myBooks);
		out.print(arr);
	}
}
