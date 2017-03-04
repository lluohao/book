package com.code.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;
import com.code.util.StreamUtils;

public class DownServlet extends HttpServlet {
	/**
	 * "application/octet-stream"
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		try {
			Integer bookId = Integer.parseInt(req.getParameter("bookId"));
			/**
			 * 判断请求的书是否存在
			 */
			if (bookId == null) {
				req.getRequestDispatcher("404.html").forward(req, res);
				return;
			}
			IBookService service = ServiceFactory.getInstance()
					.newBookService();
			Book book = service.findBookById(bookId);
			if (book == null) {
				req.getRequestDispatcher("404.html").forward(req, res);
				return;
			}
			File file = new File(PathUtil.toAbsPath(book.getContent()));
			if (!file.exists()) {
				req.getRequestDispatcher("404.html").forward(req, res);
				return;
			}
			
			/**
			 * 判断书是否免费
			 */
			if (book.getPrice() == 0) {
				res.setHeader("Content-disposition","attachment;filename=\""+book.getId()+file.getName()+"\"");
				res.setContentType("application/octet-stream");
				OutputStream os = res.getOutputStream();
				InputStream is = new FileInputStream(file);
				StreamUtils.copy(is, os);
				return;
			} else {
				/**
				 * 如果不免费，判断是书架中是否有这本书
				 */
				Integer userId = (Integer) req.getSession().getAttribute(
						"userId");
				/**
				 * 检查是否登录
				 */
				if (userId == null) {
					req.getRequestDispatcher("login.jsp").forward(req, res);
					return;
				}
				boolean has = false;
				List<Book> userBooks = service.userBooks(userId);
				for (Book book2 : userBooks) {
					if (book2.getId() == bookId) {
						has = true;
					}
				}
				if (!has) {
					req.getRequestDispatcher("payServlet").forward(req, res);
				} else {
					res.setHeader("Content-disposition","attachment;filename=\""+book.getId()+file.getName()+"\"");
					res.setContentType("application/octet-stream");
					OutputStream os = res.getOutputStream();
					InputStream is = new FileInputStream(file);
					StreamUtils.copy(is, os);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
