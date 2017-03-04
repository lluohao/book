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

public class SearchPageNoServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IBookService service = ServiceFactory.getInstance().newBookService();
		String pageNoStr = req.getParameter("pageNo");
		HttpSession session = req.getSession();
		int pageNo = 1;
		if (pageNoStr != null && !"".equals(pageNoStr)) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		String keyCode = (String) session.getAttribute("keyCode");
		List<Book> books = service
				.searchBooks(keyCode, 6, (pageNo - 1) * 6 + 1);
		req.setAttribute("pageNoAll",session.getAttribute("pageNoAll"));
		req.setAttribute("books", books);
		req.setAttribute("pageNo", pageNo);
		req.getRequestDispatcher("searchBookList.jsp").forward(req, resp);
	}
}
