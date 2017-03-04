package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.service.IRechargeService;
import com.code.service.IUserService;
import com.code.service.fact.ServiceFactory;

import net.sf.json.JSONObject;

public class AddMoneyServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String moneyStr = req.getParameter("money");
		resp.setContentType("text/html;charSet=utf-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		if (moneyStr == null && "".equals(moneyStr)) {
			json.put("code", 404);
		} else {
			try {
				int money = Integer.parseInt(moneyStr);
				IRechargeService reService = ServiceFactory.getInstance()
						.newRechargeService();
				int reId = reService.addRechargeService((Integer) req
						.getSession().getAttribute("userId"), money, -1);
				if (reId > 0) {
					IUserService userService = ServiceFactory.getInstance()
							.newUserService();
					boolean bool = userService.updateAccount((Integer) req
							.getSession().getAttribute("userId"), money, reId);
					if (bool) {
						json.put("code", 200);
					} else {
						json.put("code", 404);
					}
				} else {
					json.put("code", 404);
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.put("code", 404);
			}
		}
		out.print(json);
		out.flush();
		out.close();
	}
}
