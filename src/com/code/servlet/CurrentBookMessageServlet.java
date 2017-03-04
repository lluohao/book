package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.Book;
import com.code.read.BookPagination;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class CurrentBookMessageServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		IBookService service = ServiceFactory.getInstance().newBookService();
		PrintWriter out = res.getWriter();
		JSONObject result = new JSONObject();
		BookPagination bp = (BookPagination) req.getSession().getAttribute("bookpagination");
		if (bp != null) {
			Book book = service.findBookById(bp.getBook().getId());
			result.put("code", 200);
			result.put("name", book.getName());
			result.put("maxpage", bp.getMaxPage());
		} else {
			result.put("code", 404);
		}
		out.print(result);
	}
}
