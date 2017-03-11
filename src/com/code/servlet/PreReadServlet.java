package com.code.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.dao.ITypeDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.entity.Cart;
import com.code.entity.Collection;
import com.code.entity.Type;
import com.code.service.IBookService;
import com.code.service.ICartService;
import com.code.service.ICollectionService;
import com.code.service.fact.ServiceFactory;

public class PreReadServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int id = 0;
		String charset = req.getParameter("charset");
		if(charset==null||charset.isEmpty()){
			charset="gb2312";
		}
		String s = req.getParameter("bookId");
		id = Integer.parseInt(s);
		Book book = new Book();
		List<Book> list = new ArrayList<Book>();
		req.setAttribute("btn", "阅读全文");
		Integer userId = (Integer) session.getAttribute("userId");
		/*
		 * 根据ID找到这本书
		 */
		IBookService bookService = ServiceFactory.getInstance().newBookService();
		book = bookService.findBookById(id);
		/*
		 * 书的类型，并且转化成type类型
		 */
		ITypeDAO dao0 = DaoFactory.newInstance().newTypeDAO();
		Type type = dao0.findById(book.getType());
		/*
		 * 得类似的书，若干
		 */
		list = bookService.hotBooks(4, 1, type.getTypeId());
		Book[] books = new Book[list.size()];
		for (int i = 0; i < Math.min(list.size(), 3); i++) {
			if (list.get(i).getId() == book.getId()) {
				books[i] = list.get(i);
				req.setAttribute("book" + i, books[i]);
			} else {
				books[i] = list.get(i);
				req.setAttribute("book" + i, books[i]);
			}
		}
		req.setAttribute("isCart", "加入购物车");
		req.setAttribute("isColl", "收藏");
		req.setAttribute("isBuy", "购买");
		if (userId != null) {
			/*
			 * 在购物车中查询是否有此书
			 */
			ICartService cartser = ServiceFactory.getInstance()
					.newCartService();
			Cart cart = cartser.findCart(userId);
			if (cart != null) {
				List<BookItem> list0 = cart.getBooks();
				for (BookItem i : list0) {
					if (i.getBook().getId() == book.getId()) {
						req.setAttribute("isCart", "已加入购物车");
					}
				}
			}
			/*
			 * 收藏中是否含有此书
			 */
			ICollectionService collser = ServiceFactory.getInstance()
					.newCollectionService();
			Collection coll = collser.findCollection(userId);
			if (coll != null) {
				List<BookItem> list1 = coll.getBooks();
				System.out.println("收藏:" + list1.size());
				for (BookItem i : list1) {
					if (i.getBook().getId() == book.getId()) {
						req.setAttribute("isColl", "已收藏");
					}
				}
			}
			/*
			 * 书架中是否含有此书
			 */
			IBookService bookser = ServiceFactory.getInstance()
					.newBookService();
			List<Book> myBooks = bookser.userBooks(userId);
			if (myBooks != null) {
				for (Book i : myBooks) {
					if (i.getId() == book.getId()) {
						req.setAttribute("isBuy", "立即阅读");
					}
				}
			}
		}
		req.setAttribute("simpleText", bookService.simpleText(book.getId(),charset));
		req.setAttribute("bookName", book.getName());
		req.setAttribute("Author", book.getAuthor());
		req.setAttribute("time", book.getTime());
		req.setAttribute("price", book.getPrice() - book.getDiscount());
		req.setAttribute("oldPrice", book.getPrice());
		req.setAttribute("type", type.getTypeName());
		req.setAttribute("bookId", book.getId());
		req.setAttribute("book", book);
		req.getRequestDispatcher("preRead.jsp").forward(req, res);

	}
}
