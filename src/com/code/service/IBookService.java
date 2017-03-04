package com.code.service;

import java.util.List;

import com.code.entity.Book;

public interface IBookService {
	/**
	 * 随机推荐
	 * 
	 * @param num
	 * @return
	 */
	List<Book> randomBooks(int num);

	/**
	 * 热门book
	 * 
	 * @param num
	 * @param from
	 * @return
	 */
	List<Book> hotBooks(int num, int from);

	/**
	 * 新书上架
	 * 
	 * @param num
	 * @param from
	 * @return
	 */
	List<Book> newBooks(int num, int from);

	/**
	 * 某个类型的新书上架
	 * 
	 * @param num
	 * @param from
	 * @param typeId
	 * @return
	 */
	List<Book> newBooks(int num, int from, int typeId);

	/**
	 * 某个类型叶门book
	 * 
	 * @param num
	 * @param from
	 * @param typeId
	 * @return
	 */
	List<Book> hotBooks(int num, int from, int typeId);

	/**
	 * 查找指定价格内的书
	 * 
	 * @param typeId
	 * @param pricea
	 * @param priceb
	 * @param pageNo
	 * @param pageCount
	 * @return
	 */
	List<Book> searchByPrice(int typeId, int pricea, int priceb, int pageNo,
			int pageCount, int orderType);

	Book findBookById(int id);

	/**
	 * 用户已经购买的书
	 * 
	 * @param userId
	 * @return
	 */
	List<Book> userBooks(int userId, int page);

	List<Book> userBooks(int userId);

	/**
	 * 用户收藏的书
	 * 
	 * @param userId
	 * @return
	 */
	List<Book> userCollectionBooks(int userId);

	List<Book> userCollectionBooks(int userId, int page);

	/**
	 * 用户购物车中的书
	 * 
	 * @param userId
	 * @return
	 */
	List<Book> userCartBooks(int userId);

	/**
	 * 该类型下book的数量
	 * 
	 * @param typeId
	 * @return
	 */
	int countsByTypeId(int typeId);

	/**
	 * 该类型下book的数量
	 * 
	 * @param typeId
	 * @return
	 */
	int countsByTypeId(int typeId, int pricea, int priceb);

	/**
	 * 添加一本书
	 * 
	 * @param book
	 * @return
	 */
	int addBook(Book book);
	
	/**
	 * 管理员修改书的信息
	 */
	boolean updateBook(Book book);
	

	/**
	 * 通过关键字进行模糊查询
	 * 
	 * @param keyCode
	 * @return
	 */
	List<Book> searchBooks(String keyCode, int num, int from);
	
	/**
	 * 管理员根据书的名字查找书
	 * @param key
	 * @return
	 */
	Book findBookByName(String name);
	

	int searchBookCounts(String keyCode);

	String simpleText(int i, String charset);

}
