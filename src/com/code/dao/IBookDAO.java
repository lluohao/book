package com.code.dao;

import java.util.List;

import com.code.entity.Book;
import com.code.entity.Type;

public interface IBookDAO {
	/**
	 * 随机推荐
	 * 
	 * @param num
	 * @return
	 */
	List<Book> findBooksByIds(int[] ids);

	/**
	 * 最小的BookId
	 * 
	 * @return
	 */
	int minBookId();

	/**
	 * 最大的BookId
	 * 
	 * @return
	 */
	int maxBookId();

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
	List<Book> searchByPrice(int typeId, int minPrice, int maxPrice,
			int pageNo, int pageCount, int orderType);

	/**
	 * 通过id查找book对象
	 * @param id
	 * @return
	 */
	Book findBookById(int id);
	
	int countType(int type);
	
	int countType(int type,int minPrice,int maxPrice);
	
	int count();
	
	int addBook(Book id);
	List<Book> searchBooks(String keyCode,int num,int from);
	int searchBookCounts(String keyCode);
	
	/**
	 * 修改书籍信息
	 * @param book
	 * @return
	 */
	boolean updateBook(Book book);
	
	/**
	 * 根据书的名字找到书
	 * @param keyCode
	 * @return
	 */
	Book findBooksByKey(String name);
	
}
