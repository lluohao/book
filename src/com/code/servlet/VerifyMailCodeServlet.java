package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class VerifyMailCodeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		JSONObject result = new JSONObject();
		result.put("code", 404);
		Integer trueValue = (Integer) req.getSession().getAttribute(
				req.getParameter("key"));
		Integer value = Integer.parseInt(req.getParameter("value"));
		try {
			if (trueValue.intValue() == value.intValue()) {
				result.put("code", 200);
				req.getSession().removeAttribute(req.getParameter("key"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 500);
		}
		out.print(result);
	}
}
