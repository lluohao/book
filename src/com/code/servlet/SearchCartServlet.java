package com.code.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Cart;
import com.code.service.ICartService;
import com.code.service.fact.ServiceFactory;

public class SearchCartServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userId = -1;
		Cart cart = null;

		try {
			userId =(Integer)req.getSession().getAttribute("userId");
			ICartService cartService = ServiceFactory.getInstance()
					.newCartService();
			cart = cartService.findCart(userId);
		} catch (Exception e) {
			throw new RuntimeException("SearchCartServlet");
		}

		// 测试数据
		// List<BookItem> books = new ArrayList<BookItem>();
		// for (int i = 0; i < 3; i++) {
		// Book book = new Book();
		// book.setId(i);
		// book.setName("好页" + i);
		// BookItem bi = new BookItem();
		// bi.setBook(book);
		// bi.setRealPrice(i + 200);
		// books.add(bi);
		// }
		// cart = new Cart();
		// cart.setBooks(books);

		req.setAttribute("cart", cart);
		//req.setAttribute("userId", userId);
		req.getRequestDispatcher("cartList.jsp").forward(req, resp);
	}

}
