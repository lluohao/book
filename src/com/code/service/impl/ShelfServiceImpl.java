package com.code.service.impl;

import java.util.List;

import com.code.dao.IShelfDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.BookItem;
import com.code.entity.Shelf;
import com.code.service.IShelfService;

/**
 * 书架service
 * 
 * @author psn0183pp
 * 
 */

public class ShelfServiceImpl implements IShelfService {
	private IShelfDAO dao = DaoFactory.newInstance().newShelfDAO();

	@Override
	public int addShelf(int bookId, int userId) {
		dao.removeShelf(bookId, userId);
		return dao.addShelf(bookId, userId);

	}

	@Override
	public boolean removeShelf(int bookId, int userId) {
		return dao.removeShelf(bookId, userId);
	}

	@Override
	public Shelf findShelf(int userId) {
		return dao.findShelfById(userId);
	}

}
