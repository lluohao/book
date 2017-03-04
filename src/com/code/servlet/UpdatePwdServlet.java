package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.md5.MD5Encoding;
import com.code.service.IUserService;
import com.code.service.fact.ServiceFactory;

public class UpdatePwdServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId=(Integer) request.getSession().getAttribute("userId");
		
		String password=(String)request.getParameter("newPwd");
		
		IUserService userService=ServiceFactory.getInstance().newUserService();
		boolean boo=userService.updatePassword(userId, password);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(boo){
			request.getSession().removeAttribute("userId");
			out.print("修改成功");
		}else{
			out.print("修改失败");
		}
		
	}
	
}
