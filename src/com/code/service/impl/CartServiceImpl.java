package com.code.service.impl;

import com.code.dao.ICartDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Cart;
import com.code.service.ICartService;

/**
 * 购物车Service
 * 
 * @author psn0183pp
 * 
 */
public class CartServiceImpl implements ICartService {
	private ICartDAO dao = DaoFactory.newInstance().newCartDAO();

	@Override
	public int addBook(int bookId, int userId) {
		dao.removeBook(bookId, userId);
		return dao.addBook(bookId, userId);

	}

	@Override
	public boolean removeBook(int bookId, int userId) {
		return dao.removeBook(bookId, userId);
	}

	@Override
	public Cart findCart(int userId) {
		return dao.findCartByUserId(userId);
	}

}
