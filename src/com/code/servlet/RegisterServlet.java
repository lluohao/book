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

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String password = req.getParameter("pwd");
		String email = req.getParameter("email");
		IUserService service = UserServcieFactory.getInstance()
				.newUserService();
		int id = service.addUser(name, password, email);
		JSONObject jObj = new JSONObject();
		jObj.put("code", "200");
		jObj.put("id", id);
		out.print(jObj);
		out.flush();
	}
}
