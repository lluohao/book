package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;

public class MyShelfServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int userId=(Integer)session.getAttribute("userId");
		int pageNo=1;
		try{
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}catch(Exception e){}
		
		
		IBookService bookService=ServiceFactory.getInstance().newBookService();
		List<Book> bookList=bookService.userBooks(userId);
		int count=bookList.size();
		int pageAll=count%14==0? count/14:count/14+1;
		
		List<Book> bookLists=bookService.userBooks(userId,pageNo);
		request.setAttribute("bookLists", bookLists);
		request.setAttribute("pageAll", pageAll);
		request.setAttribute("pageNo", pageNo);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("/myBookShelf.jsp").forward(request, response);
		
	}
	
}
