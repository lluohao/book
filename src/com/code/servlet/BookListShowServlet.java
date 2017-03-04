package com.code.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Cart;
import com.code.entity.Type;
import com.code.service.ICartService;
import com.code.service.ITypeService;
import com.code.service.fact.ServiceFactory;

public class BookListShowServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ITypeService service = ServiceFactory.getInstance().newTypeService();
		int typeId = 1;
		try {
			typeId = Integer.parseInt(req.getParameter("typeId"));
		} catch (Exception e) {
		}
		String order = req.getParameter("orderType");
		if (order == null || "".equals(order)) {
			order = "hot";
		}
		if ("count".equals(order)) {
			try {
				req.getSession().setAttribute("numa",
						Integer.parseInt(req.getParameter("numa")));
			} catch (Exception e) {
				req.getSession().setAttribute("numa", 0);
			}
			try {
				req.getSession().setAttribute("numb",
						Integer.parseInt(req.getParameter("numb")));
			} catch (Exception e) {
				req.getSession().setAttribute("numb", Integer.MAX_VALUE);
				req.setAttribute("numb", "不限");
			}
		} else {
			req.getSession().setAttribute("numa", "");
			req.getSession().setAttribute("numa", "");
		}

		// 判断该分类是子分类还是父类
		boolean bool = service.isParentType(typeId);
		// 得到父分类
		Type parentType = bool ? service.findById(typeId) : service
				.parentType(typeId);
		// 得到所有的子分类
		List<Type> types = service.allChildType(parentType.getTypeId());
		// 发送被选中的分类
		ICartService cartService = ServiceFactory.getInstance()
				.newCartService();
		try {
			int userId = (Integer) req.getSession().getAttribute("userId");
			Cart cart = cartService.findCart(userId);
			List<String> bookIds = new ArrayList<String>();
			for (int i = 0; i < cart.getBooks().size(); i++) {
				bookIds.add(""+cart.getBooks().get(i).getBook().getId());
			}	
			req.getSession().setAttribute("bookIds", bookIds);
		} catch (Exception e) {
		}
		Type selectType = service.findById(typeId);
		req.setAttribute("orderType", order);
		req.setAttribute("types", types);
		req.setAttribute("parentType", parentType);
		req.setAttribute("selectType", selectType);
		req.getRequestDispatcher("bookListShow.jsp").forward(req, resp);
	}
}
