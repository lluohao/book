package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.IOrderService;
import com.code.service.fact.ServiceFactory;

public class PayServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String[] bookIdStrs = req.getParameterValues("bookId");
		String dataType = req.getParameter("dataType");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();

		try {
			int userId = (Integer) req.getSession().getAttribute("userId");
			IBookService service = ServiceFactory.getInstance()
					.newBookService();
			int accounts = 0;
			int bookNums = 0;
			int[] bookIds = new int[bookIdStrs.length];
			for (int i = 0; i < bookIdStrs.length; i++) {
				bookIds[i] = Integer.parseInt(bookIdStrs[i]);
				if (bookIdStrs[i] != null && !"".equals(bookIdStrs[i])) {
					int bookId = bookIds[i];
					Book book = service.findBookById(bookId);
					accounts += (book.getPrice() - book.getDiscount());
					bookNums++;
				}
			}
			IOrderService orderService = ServiceFactory.getInstance()
					.newOrderService();
			int orderId = orderService.addOrder(bookIds, userId);
			req.setAttribute("orderId", orderId);
			req.setAttribute("mab", accounts);
			req.setAttribute("bookNums", bookNums);
			req.setAttribute("rmb", (accounts + 0.0) / 100);
			json.put("code", "200");
			json.put("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("code", "404");
			json.put("code", "404");
		}
		if (dataType == null || "".equals(dataType)) {
			req.getRequestDispatcher("pay.jsp").forward(req, resp);
		} else {
			out.print(json);
			out.flush();
			out.close();
		}
	}
}
