package com.code.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class IndexServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		IBookService bookService = ServiceFactory.getInstance()
				.newBookService();
		List<Book> randomBooks = bookService.randomBooks(10);
		List<Book> hotBooks = bookService.hotBooks(10, 1);
		List<Book> newBooks = bookService.newBooks(10, 1);
		req.setAttribute("randomBooks", randomBooks);
		req.setAttribute("hotBooks", hotBooks);
		req.setAttribute("newBooks", newBooks);
		req.getRequestDispatcher("/index.jsp").forward(req, res);
	}
}
