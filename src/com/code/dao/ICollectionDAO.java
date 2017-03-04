package com.code.dao;

import java.sql.Connection;

import com.code.entity.Collection;
import com.code.jdbc.DBConnection;


public interface ICollectionDAO {
	/**
	 * 添加
	 * 
	 * @return
	 */
	int addCollection(int userId,int bookId);
	/**
	 * 移除
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean removeCollection(int bookId, int userId);
	
	/**
	 * 根据用户id以及书籍id删除用户收藏的书
	 * @param bookIds
	 * @param userId
	 * @return
	 */
	boolean removeCollection(int[] bookIds, int userId);
	/**
	 * 根据userId查找collection
	 * @param userId
	 * @return
	 */
	Collection findCollectionByUserId(int userId);
}
