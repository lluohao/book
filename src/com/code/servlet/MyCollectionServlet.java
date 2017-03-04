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

public class MyCollectionServlet extends HttpServlet {

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

		
		List<Book> bookList=bookService.userCollectionBooks(userId);
		int count=0;
		if(bookList!=null){
			count=bookList.size();
		}

		int pageAll=count%12==0? count/12:count/12+1;
		
		List<Book> bookLists=bookService.userCollectionBooks(userId,pageNo);
		request.setAttribute("pageAll", pageAll);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bookLists", bookLists);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("/myFavor.jsp").forward(request, response);
	}
	
}
