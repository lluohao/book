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

public class PageNoServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int typeId = 1;
		String orderType= req.getParameter("orderType");
		HttpSession session = req.getSession();
		if (orderType == null) {
			orderType = (String) session.getAttribute("orderType");
		} else {
			session.setAttribute("orderType", orderType);
		}
		try {
			typeId = Integer.parseInt(req.getParameter("typeId"));
			session.setAttribute("typeId", typeId);
		} catch (NullPointerException e) {
			typeId = (Integer) session.getAttribute("typeId");
		} catch (Exception e) {
		}
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
		} catch (Exception e) {
		}
		IBookService service = ServiceFactory.getInstance().newBookService();
		int pageAll = 0;
		if (req.getParameter("orderType") == null
				|| "".equals(req.getParameter("orderType"))) {
			pageAll = (Integer) session.getAttribute("pageAll");
		} else {
			int counts = 0;
			if ("count".equals(req.getParameter("orderType"))) {
				int numa = (Integer) session.getAttribute("numa");
				int numb = (Integer) session.getAttribute("numb");
				counts = service.countsByTypeId(typeId, numa, numb);
			} else
				counts = service.countsByTypeId(typeId);
			pageAll = counts % 8 == 0 ? counts / 8 : (counts / 8 + 1);
			session.setAttribute("pageAll", pageAll);
		}
		int from = (pageNo - 1) * 8 + 1;
		List<Book> books = null;
		if ("hot".equals(orderType)) {
			books = service.hotBooks(8, from, typeId);
		} else if ("count".equals(orderType)) {
			int numa = (Integer) session.getAttribute("numa");
			int numb = (Integer) session.getAttribute("numb");
			int orderBy = numa > numb ? -1 : 1;
			books = service.searchByPrice(typeId, numa, numb, pageNo, 8,
					orderBy);
		} else if ("new".equals(orderType)) {
			books = service.newBooks(8, from, typeId);
		}
		req.setAttribute("msg", pageAll == 0 ? "没有找到相关书籍。。。" : "");
		req.setAttribute("books", books);
		req.setAttribute("pageAll", pageAll);
		req.setAttribute("pageNo", pageNo);
		req.getRequestDispatcher("booksList.jsp").forward(req, resp);
	}
}
