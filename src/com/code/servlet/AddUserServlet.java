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

public class AddUserServlet extends HttpServlet {
	/**
	 * superuser专用添加用户Servlet
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charSet=utf-8");
		String name;
		String password;
		String email;
		int account;
		char sex;
		int age;
		int status;
		IUserService service;
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		String result;
		User user = null;

		try {
			// 获取user信息并封装成User
			name = req.getParameter("name");
			password = req.getParameter("password");
			email = req.getParameter("email");
			user = new User();
			String account_str = req.getParameter("account");
			if (!"".equals(account_str)) {
				account = Integer.parseInt(account_str);
				user.setAccount(account);
			}
			sex = "男".equals(req.getParameter("sex")) ? '男' : '女';
			String age_str = req.getParameter("age");
			if (!"".equals(age_str)) {
				age = Integer.parseInt(age_str);
				user.setAge(age);
			}
			String status_str = req.getParameter("status");
			if (!"".equals(status_str)) {
				status = Integer.parseInt(status_str);
				user.setStatus(status);
			}
			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
			user.setSex(sex);

			// 向数据库插入数据并返回结果
			service = UserServcieFactory.getInstance().newUserService();
			int count = service.addUser(user);
			if (count > 0) {
				result = "添加用户成功";
			} else if (count == -8) {
				result = "用户名长度必须在2~20之间";
			} else if (count == -2) {
				result = "用户名已存在";
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
			} else if (count == -9) {
				result = "密码长度必须在6~16之间";
			} else {
				result = "添加失败";
			}
			json.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			result = "添加失败";
			json.put("result", result);
		}
		out.print(json);
	}

}
