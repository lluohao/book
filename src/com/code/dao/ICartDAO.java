package com.code.dao;

import com.code.entity.Cart;


public interface ICartDAO {
	/**
	 * 添加
	 * 
	 * @return
	 */
	int addBook(int bookId,int userId);

	/**
	 * 从某人的购物车中移除一本书
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean removeBook(int bookId, int userId);
	/**
	 * 根据userId查找cart
	 * @param userId
	 * @return
	 */
	Cart findCartByUserId(int userId);
}
