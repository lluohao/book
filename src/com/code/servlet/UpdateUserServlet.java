package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import com.code.dao.fact.UserServcieFactory;
import com.code.entity.User;
import com.code.service.IUserService;

public class UpdateUserServlet extends HttpServlet {
	/**
	 * superuser专用宇宙无敌修改用户信息Servlet
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charSet=utf-8");
		int userId;
		String name;
		String email;
		int account;
		char sex;
		int age;
		int status;
		IUserService service;
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		User user;
		String result;
		try {
			service = UserServcieFactory.getInstance().newUserService();
			name = req.getParameter("name");
			email = req.getParameter("email");
			// 获取user信息并封装成User
			System.out.println(req.getParameter("id"));
			userId = Integer.parseInt(req.getParameter("id"));
			user = service.findUser(userId);

			String account_str = req.getParameter("account");
			if (account_str != null) {
				account = Integer.parseInt(account_str);
				user.setAccount(account);
			}
			sex = "男".equals(req.getParameter("sex")) ? '男' : '女';

			String age_str = req.getParameter("age");
			if (age_str != null) {
				age = Integer.parseInt(age_str);
				user.setAge(age);
			}
			String status_str = req.getParameter("status");
			if (status_str != null) {
				status = Integer.parseInt(status_str);
				user.setStatus(status);
			}

			user.setEmail(email);
			user.setName(name);
			user.setSex(sex);
			System.out.println(user);

			// 修改数据库数据并返回结果
			int count = service.updateUser(user);
			if (count > 0) {
				result = "修改成功";
			} else if (count == -2) {
				result = "用户名长度必须在2~20之间";
			} else if (count == -3) {
				result = "用户金额不合法";
			} else if (count == -4) {
				result = "年龄必须在0~150之间";
			} else if (count == -5) {
				result = "用户状态错误";
			} else if (count == -6) {
				result = "性别错误";
			} else if (count == -7) {
				result = "邮箱错误";
			} else {
				result = "修改失败";
			}
			System.out.println(count);
			json.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			result = "修改失败";
		}
		out.print(json);
	}

}
