package com.code.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class SearchBooksServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IBookService service = ServiceFactory.getInstance().newBookService();
		String keyCode = req.getParameter("keyCode").trim();
		if (keyCode == null || "".equals(keyCode)) {
			req.setAttribute("pageNoAll", 0);
		} else {
			int pageNoAll = 0;
			HttpSession session = req.getSession();
			session.setAttribute("keyCode", keyCode);
			int counts = service.searchBookCounts(keyCode);
			pageNoAll = counts / 6 + (counts % 6 == 0 ? 0 : 1);
			session.setAttribute("pageNoAll", pageNoAll);
			req.setAttribute("size",counts);
		}
		List<Book> hotBooks = service.hotBooks(4, 1);		
		req.setAttribute("hotBooks", hotBooks);
		req.setAttribute("keyCode", keyCode);
		req.getRequestDispatcher("searchBooks.jsp").forward(req, resp);
	}
}
