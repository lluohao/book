package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.User;
import com.code.mail.MailSender;
import com.code.service.IUserService;
import com.code.service.fact.ServiceFactory;

public class RequestEmailCodeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		int userId = Integer.parseInt(req.getParameter("userId"));
		IUserService service = ServiceFactory.getInstance().newUserService();
		User user = service.findUser(userId);
		JSONObject result = new JSONObject();
		int value = (int) (Math.random() * (999999 - 100000) + 1000000);
		long key = System.currentTimeMillis();
		req.getSession().setAttribute("mailcode" + key, value);
		if (user != null) {
			String email = user.getEmail();
			if (email == null) {
				result.put("code", 404);
			} else {
				MailSender sender = new MailSender();
				try {
					sender.sendMail("皓叶电子书验证码", "阁下正在申请皓叶电子书验证码，本次验证码为："
							+ value, email);
					result.put("code", 200);
					result.put("key", "mailcode" + key);
				} catch (MessagingException e) {
					e.printStackTrace();
					result.put("code", 500);
				}
			}
		}
		out.print(result);
	}
}
