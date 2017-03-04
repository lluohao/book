package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.User;
import com.code.service.IUserService;
import com.code.service.fact.ServiceFactory;

public class GetUserMsgServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userIdStr=request.getParameter("userId");
		int userId=0;
		try{
			userId=Integer.parseInt(userIdStr);
		}catch(Exception e){}
		IUserService userService=ServiceFactory.getInstance().newUserService();
		User user=userService.findUser(userId);
		
		JSONObject obj=JSONObject.fromObject(user);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		out.print(obj.toString());
	}
	
}
