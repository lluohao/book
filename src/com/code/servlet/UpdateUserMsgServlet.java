package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.IUserService;
import com.code.service.fact.ServiceFactory;

public class UpdateUserMsgServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userIdStr=request.getParameter("userId");
		String sexStr=request.getParameter("sex");
		String ageStr=request.getParameter("age");
		String statusStr=request.getParameter("status");
		int sex=0;
		int userId=0;
		int age=0;
		int status=0;
		try{
			userId=Integer.parseInt(userIdStr);
			sex=Integer.parseInt(sexStr);
			age=Integer.parseInt(ageStr);
			status=Integer.parseInt(statusStr);
		}catch(Exception e){}
		IUserService userService=ServiceFactory.getInstance().newUserService();
		
		
		boolean judge=userService.updateUser(userId, sex, age, status);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(judge){
			out.println("修改成功");
		}else{
			out.println("修改失败");
		}
	}
	
}
