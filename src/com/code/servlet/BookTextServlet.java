package com.code.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.read.BookDate;
import com.code.read.BookPagination;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;

public class BookTextServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		int id = Integer.parseInt(req.getParameter("bookId"));
		int page = Integer.parseInt(req.getParameter("page"));
		int lines = Integer.parseInt(req.getParameter("lines"));// 行数
		int rows = Integer.parseInt(req.getParameter("rows"));// 列数
		IBookService bookService = ServiceFactory.getInstance()
				.newBookService();
		Book book = bookService.findBookById(id);
		BookPagination bp = (BookPagination) req.getSession().getAttribute(
				"bookpagination");
		if (bp == null || bp.getBook().getId() != book.getId()
				|| bp.getRows() != rows || bp.getCols() != lines) {
			if (book != null) {
				FileInputStream fis = new FileInputStream(
						PathUtil.toAbsPath(book.getContent()));
				BookDate bookDate = new BookDate(fis);
				bookDate.setId(book.getId());
				bp = new BookPagination(bookDate, rows, lines);
				req.getSession().setAttribute("bookpagination", bp);
			}
		}
		out.print(bp.getPage(page).toString().replace("\n\n", "\n"));
	}
}
