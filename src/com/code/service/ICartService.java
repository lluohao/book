package com.code.service;

import com.code.entity.Cart;


public interface ICartService {
	/**
	 * 添加一本书至某个人的购物车
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	int addBook(int bookId, int userId);

	/**
	 * 从某人的购物车中移除一本书
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	boolean removeBook(int bookId, int userId);
	
	Cart findCart(int userId);
	
}
