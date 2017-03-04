package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.ICollectionService;
import com.code.service.fact.ServiceFactory;

public class AddCollectionServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookIdStr=request.getParameter("bookId");
		int userId=(Integer)request.getSession().getAttribute("userId");
		int bookId=0;
		try{
			bookId=Integer.parseInt(bookIdStr);
		}catch(Exception e){}
		ICollectionService service=ServiceFactory.getInstance().newCollectionService();
		int collectId=service.addCollection(bookId, userId);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(collectId>0){
			out.print(true);
		}else{
			out.print(false);
		}
	}
	
}
