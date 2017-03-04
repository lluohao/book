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

public class ForgetPwdServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charSet=utf-8");
		IUserService userService=UserServcieFactory.getInstance().newUserService();
		PrintWriter out = resp.getWriter();
		String userName="";
		String type;
		JSONObject json=new JSONObject();
		try {
			type=req.getParameter("type").trim();
			userName=req.getParameter("userName").trim();
			User user=userService.findUser(userName);
			if("hasUser".equals(type)){
				if(user!=null){
					json.put("hasUser", true);
					json.put("email", user.getEmail());
				}else{
					json.put("hasUser", false);
				}
			}else if("send".equals(type)){
				if(user!=null){
					req.getRequestDispatcher("requestEmailCodeServlet?userId="+user.getId()).forward(req, resp);
				}
			}
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
