package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class FindBooksByKeyServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookName=request.getParameter("bookName");
//		System.out.println(bookName);
		
		IBookService service=ServiceFactory.getInstance().newBookService();
		Book book=service.findBookByName(bookName);
		
//		System.out.println(book.getDiscribe());
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		JSONObject obj=JSONObject.fromObject(book);
		
		out.print(obj.toString());
	}

	
}
