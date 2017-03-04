package com.code.service;

import com.code.entity.Collection;

public interface ICollectionService {
	/**
	 * 添加
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	int addCollection(int bookId, int userId);

	/**
	 * 移除
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean removeCollection(int bookId, int userId);
	
	boolean removeCollection(int[] bookIds, int userId);
	
	Collection findCollection(int userId);
}
