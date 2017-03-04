package com.code.dao;

import com.code.entity.Shelf;


public interface IShelfDAO {
	/**
	 * 添加
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	int addShelf(int bookId,int userId);

	/**
	 * 移除
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean removeShelf(int bookId, int userId);
	
	/**
	 * 根据用户id查找书架
	 * @param userId
	 * @return
	 */
	Shelf findShelfById(int userId);
}
