package com.code.service;

import com.code.entity.Shelf;

public interface IShelfService {
	/**
	 * 添加
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	int addShelf(int bookId, int userId);

	/**
	 * 移除
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean removeShelf(int bookId, int userId);
	
	Shelf findShelf(int userId);
}
