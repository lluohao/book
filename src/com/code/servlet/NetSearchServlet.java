package com.code.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.net.NetBook;
import com.code.service.INetBookService;
import com.code.service.fact.ServiceFactory;

public class NetSearchServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String key = req.getParameter("keyCode");
		INetBookService service = ServiceFactory.getInstance()
				.newNetBookservice();
		List<Book> books = service.search(key);
		req.getSession().setAttribute("netbooks", books);
		req.setAttribute("books", books);
		req.setAttribute("size", books.size());
		req.setAttribute("keyCode", key);
		req.getRequestDispatcher("netSearch.jsp").forward(req, res);
		
	}
}
