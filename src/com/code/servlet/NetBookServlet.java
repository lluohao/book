package com.code.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.INetBookService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;

public class NetBookServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		JSONObject obj = new JSONObject();
		try {
			String key = req.getParameter("dkey");
			if (key != null) {
				req.getSession().setAttribute("netdownload" + key, -1);
			}
			INetBookService service = ServiceFactory.getInstance()
					.newNetBookservice();
			IBookService serviceBook = ServiceFactory.getInstance()
					.newBookService();
			int id = Integer.parseInt(req.getParameter("id"));
			List<Book> books = (List<Book>) req.getSession().getAttribute(
					"netbooks");
			String path = PathUtil.toAbsPath("root/book/net/" + books.get(id).getName()+ ".txt");
			File file = new File(path);
			req.getSession().setAttribute("bookfile" + key, file);
			Book book = service.downloadBook(books.get(id));
			book.setTime(new Timestamp(System.currentTimeMillis()));
			if (book.getContent() != null) {
				int bookId = serviceBook.addBook(book);
				book.setId(bookId);
				obj.put("code", 200);
				obj.put("bookId", bookId);
			} else {
				obj.put("code", 404);
			}
			if (key != null) {
				req.getSession().setAttribute("netdownload" + key, book.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.print(obj);
	}
}
