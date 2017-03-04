package com.code.service.impl;

import com.code.dao.ICollectionDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Collection;
import com.code.service.ICollectionService;

/**
 * 收藏书service
 * 
 * @author psn0183pp
 * 
 */
public class CollectionServiceImpl implements ICollectionService {
	private ICollectionDAO dao = DaoFactory.newInstance().newCollectionDAO();

	@Override
	public int addCollection(int bookId, int userId) {
		dao.removeCollection(bookId, userId);
		return dao.addCollection(userId, bookId);

	}

	@Override
	public boolean removeCollection(int bookId, int userId) {
		return dao.removeCollection(bookId, userId);
	}

	@Override
	public Collection findCollection(int userId) {
		return dao.findCollectionByUserId(userId);
	}

	@Override
	public boolean removeCollection(int[] bookIds, int userId) {
		return dao.removeCollection(bookIds, userId);
	}

}
