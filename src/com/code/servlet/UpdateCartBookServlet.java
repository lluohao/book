package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.service.ICartService;
import com.code.service.fact.ServiceFactory;

public class UpdateCartBookServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charSet=utf-8");
		PrintWriter out = resp.getWriter();
		int userId = -1;
		String[] bookIds = null;
		String type;
		int cartBookNum;
		JSONObject json = json = new JSONObject();
		List<Integer> fail = new ArrayList<Integer>();
		try {
			userId = (Integer) req.getSession().getAttribute("userId");
			bookIds = req.getParameterValues("bookId");

			type = req.getParameter("type");
			
			ICartService cartService = ServiceFactory.getInstance()
					.newCartService();
			int bookId;

			if ("remove".equals(type)) {
				// 删除书籍
				for (int i = 0; i < bookIds.length; i++) {
					if (!"".equals(bookIds[i].trim())) {

						bookId = Integer.parseInt(bookIds[i]);
						if (bookId >= 0) {
							if (!cartService.removeBook(bookId, userId)) {
								fail.add(bookId);
							}
						}
					}
				}
				if (fail.size() == 0) {
					json.put("isDelete", true);
				} else {
					json.put("isDelete", false);
					json.put("failBooks", fail);
				}
			} else if ("add".equals(type)) {
				// 添加书籍
				for (int i = 0; i < bookIds.length; i++) {
					if (!"".equals(bookIds[i].trim())) {
						bookId = Integer.parseInt(bookIds[i]);
						if (bookId >= 0) {
							if (cartService.addBook(bookId, userId) < 0) {
								fail.add(bookId);
							}
							;
						}
					}
				}
				if (fail.size() == 0) {
					json.put("isAdd", true);
					try{
					List<String> cartBookIds = (List<String>) req
							.getSession().getAttribute("bookIds");
					for (int i = 0; i < bookIds.length; i++) {
						if (!"".equals(bookIds[i].trim())) {
							cartBookIds.add(bookIds[i]);
						}
					}}catch(Exception e){}
				} else {
					json.put("isAdd", false);
					json.put("failBooks", fail);
				}
			}
			cartBookNum = cartService.findCart(userId).getBooks().size();
			json.put("cartNum", cartBookNum);
			out.print(json);
		} catch (Exception e) {
			json.put("isAdd", false);
			json.put("isDelete", false);
			json.put("message", "没有登录，操作失败");
			out.print(json);
		}
	}
}
