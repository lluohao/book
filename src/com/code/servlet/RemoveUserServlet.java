package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.dao.fact.UserServcieFactory;
import com.code.service.IUserService;

public class RemoveUserServlet extends HttpServlet {
	/**
	 * superuser专用删除用户Servlet
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charSet=utf-8");
		int userId;
		PrintWriter out = resp.getWriter();
		JSONObject json=new JSONObject();
		try {
			//获取需要删除的用户Id
			String userId_str = req.getParameter("userId");
			if (userId_str != null) {
				userId = Integer.parseInt(userId_str);
				IUserService servlet = UserServcieFactory.getInstance()
						.newUserService();
				if (servlet.removeUser(userId)) {
					json.put("result","true");
				} else {
					json.put("result","false");
				}
			}else{
				json.put("result","null");
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("result","false");
		}
	}

}
