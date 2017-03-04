package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.dao.fact.UserServcieFactory;
import com.code.entity.User;
import com.code.service.IUserService;

public class FindUserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charSet=utf-8");
		String userName;
		int userId;
		PrintWriter out = resp.getWriter();
		User user=null;
		JSONObject result=new JSONObject();
		IUserService userService = UserServcieFactory.getInstance()
				.newUserService();
		try {
			String userId_str = req.getParameter("userID");
			userName = req.getParameter("userName");
			if (userId_str != null) {
				userId = Integer.parseInt(userId_str);
				user=userService.findUser(userId);
			}else if(userName!=null){
				user = userService.findUser(userName);
			}
			if(user==null){
				user=new User();
			}
			result.put("user",user);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
