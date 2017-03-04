package com.code.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class ReadServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(req.getParameter("bookId"));
			IBookService bookService = ServiceFactory.getInstance()
					.newBookService();
			Book book = bookService.findBookById(id);
			if (book == null) {
				req.getRequestDispatcher("404.html").forward(req, res);
			} else if (book.getContent().toLowerCase().endsWith(".txt")) {
				req.setAttribute("bookId", id);
				req.getRequestDispatcher("read.jsp").forward(req, res);
			} else if (book.getContent().toLowerCase().endsWith(".pdf")) {
				req.setAttribute("name", book.getName());
				req.setAttribute("url", "pdfContentServlet?id="+id);
				req.getRequestDispatcher("pdf/web/viewer.jsp").forward(req, res);
			}
		} catch (Exception e) {
			req.getRequestDispatcher("404.html").forward(req, res);
		}
	}
}
