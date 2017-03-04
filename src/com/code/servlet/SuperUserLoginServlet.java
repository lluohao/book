package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.code.dao.fact.UserServcieFactory;
import com.code.service.ISuperUserService;

@SuppressWarnings("serial")
public class SuperUserLoginServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		ISuperUserService service = UserServcieFactory.getInstance()
				.newSuperUserService();

		JSONObject jObj = new JSONObject();
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		int id = service.findSuperUser(name, pwd);
		/**
		 * 处理登录成功后的逻辑
		 */
		if (id > 0) {
			HttpSession sessions = req.getSession();
			sessions.setAttribute("userId", id);

		}
		jObj.put("code", 200);
		jObj.put("id", id);
		out.print(jObj.toString());
		out.flush();
	}
}
