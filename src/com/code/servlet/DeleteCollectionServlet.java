package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.ICollectionService;
import com.code.service.fact.ServiceFactory;

public class DeleteCollectionServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userIdStr=request.getParameter("userId");
		String bookIdsStr=request.getParameter("bookIds");
		
		String[] idStrs=bookIdsStr.split(",");
		int[] bookIds=null;
		if(idStrs!=null){
			bookIds=new int[idStrs.length-1];
			for(int i=0;i<idStrs.length-1;i++){
				bookIds[i]=Integer.parseInt(idStrs[i+1]);
			}
			
		}
		
		int userId=0;
		try{
			userId=Integer.parseInt(userIdStr);
		}catch(Exception e){}
		ICollectionService collect=ServiceFactory.getInstance().newCollectionService();
		boolean boo=collect.removeCollection(bookIds, userId);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(boo){
			out.print("删除成功");
		}else{
			out.print("删除失败");
		}
	
	}
	
}
