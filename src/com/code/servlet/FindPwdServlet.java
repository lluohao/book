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

public class FindPwdServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject result=new JSONObject();
		PrintWriter out=resp.getWriter();
		try {
			String userName=req.getParameter("userName");
			String password=req.getParameter("password");
			IUserService service=ServiceFactory.getInstance().newUserService();
			User user=service.findUser(userName);
			int userId=user.getId();
			boolean isUpdate=service.updatePassword(userId, password);
			if(isUpdate){
				result.put("code",200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(result);
		
	}
	
}
