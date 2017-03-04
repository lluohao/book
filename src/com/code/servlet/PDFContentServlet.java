package com.code.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;
import com.code.util.StreamUtils;

public class PDFContentServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		OutputStream os = resp.getOutputStream();
		int id = Integer.parseInt(req.getParameter("id"));
		IBookService service = ServiceFactory.getInstance().newBookService();
		Book book = service.findBookById(id);
		FileInputStream is = new FileInputStream(PathUtil.toAbsPath(book
				.getContent()));
		StreamUtils.copy(is, os);
		os.flush();
		is.close();
	}
}
