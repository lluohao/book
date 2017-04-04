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
		Integer userId= null;
		int bookId=0;
		PrintWriter out=response.getWriter();
		try{
			userId = (Integer)request.getSession().getAttribute("userId");
			bookId=Integer.parseInt(bookIdStr);
		}catch(Exception e){
		}
		if(userId==null||userId<0){
			out.print(false);
			return;
		}
		ICollectionService service=ServiceFactory.getInstance().newCollectionService();
		int collectId=service.addCollection(bookId, userId);
		response.setContentType("text/html;charset=UTF-8");
		if(collectId>0){
			out.print(true);
		}else{
			out.print(false);
		}
	}
	
}
