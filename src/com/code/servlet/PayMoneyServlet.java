package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.BookItem;
import com.code.service.IOrderService;
import com.code.service.IRechargeService;
import com.code.service.IShelfService;
import com.code.service.IUserService;
import com.code.service.fact.ServiceFactory;

public class PayMoneyServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String type = req.getParameter("type");
		String orderIdStr = req.getParameter("orderId");
		resp.setContentType("text/html;charSet=utf-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		if (type == null || "".equals(type) || orderIdStr == null
				|| "".equals(orderIdStr)) {
			json.put("code", 404);
		} else {
			IOrderService orderService = ServiceFactory.getInstance()
					.newOrderService();
			int orderId = Integer.parseInt(orderIdStr);
			IUserService userService = ServiceFactory.getInstance()
					.newUserService();
			List<BookItem> bookItems = orderService.findOrder(orderId)
					.getBooks();
			int price = 0;
			for (BookItem bookItem : bookItems) {
				price += bookItem.getRealPrice();
			}
			try {
				int userId = (Integer) req.getSession().getAttribute("userId");
				if (type.equals("rmb")) {
					json.put("code", 200);
				} else if (type.equals("mab")) {
					int account = userService.findUser(userId).getAccount();
					if (account - price >= 0) {
						IRechargeService reService = ServiceFactory
								.getInstance().newRechargeService();
						int i = reService.addRechargeService(userId,
								-price , -1);
						if (i > 0) {			
							userService.updateAccount(userId,
									-price,i);
							json.put("code", 200);
						}else{
							json.put("code", 404);
						}		
					} else {
						json.put("code", 404);
						json.put("msg", "账户余额不足");
					}
				}
				if ((Integer) json.get("code") == 200) {
					boolean bool = orderService.updateStatus(orderId, 1, -1);
					if (!bool) {
						json.put("code", 404);
					} else {
						IShelfService shelfService = ServiceFactory
								.getInstance().newShelfService();
						for (BookItem bookItem : bookItems) {
							shelfService.addShelf(bookItem.getBook().getId(),
									userId);
						}
					}
				}
			} catch (Exception e) {
				json.put("code", 404);
				e.printStackTrace();
			}
		}
		out.print(json);
		out.flush();
		out.close();
	}
}
