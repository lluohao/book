package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.BookItem;
import com.code.entity.Shelf;
import com.code.service.IShelfService;
import com.code.service.fact.ServiceFactory;

public class ShelfFindBookServlet extends HttpServlet {
	/**
	 * 从书架中查询是否含有该书
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String userIds=req.getParameter("userId");
		String s=req.getParameter("bookId");
		int bookId=Integer.parseInt(s);
		int userId=Integer.parseInt(userIds);
		IShelfService service=ServiceFactory.getInstance().newShelfService();
		Shelf shelf=service.findShelf(userId);
		List<BookItem> list=shelf.getBooks();
		PrintWriter out=res.getWriter();
		boolean buy = false;
		for(BookItem i:list){
			if(i.getBook().getId()==bookId){
				buy = true;
			}
		}
		out.print(buy);
		
	}
	
}
