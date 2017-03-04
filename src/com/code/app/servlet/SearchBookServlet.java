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

public class SearchBookServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String key = req.getParameter("keyword");
		IBookService service = ServiceFactory.getInstance().newBookService();
		if (key == null || key.length() == 0) {
			List<Book> books = service.hotBooks(60, 1);
			JSONArray arr = new JSONArray();
			arr.addAll(books);
			out.print(arr);
			return;
		}
		List<Book> books = service.searchBooks(key, 30, 1);
		JSONArray arr = new JSONArray();
		arr.addAll(books);
		out.print(arr);
	}
}
