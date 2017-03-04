package com.code.util;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.code.dao.IBookDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.entity.Cart;
import com.code.entity.Collection;

public class CollectionUtil {
	/**
	 *  从resultset中解析出一个collection对象
	 * @param rs
	 * @return
	 * @throws Exception 
	 */
	public static Collection parseCollection(ResultSet rs) throws Exception{
		Collection collection=new Collection();
		IBookDAO bookdao=DaoFactory.newInstance().newBookDAO();
		List<BookItem> lists=new ArrayList<BookItem>();
		lists=FindBooks.findCollBooks(rs.getInt("collection_userid"));
		collection.setId(rs.getInt("collection_id"));
		collection.setUserID(rs.getInt("collection_userid"));
		collection.setBooks(lists);
		return collection;
	}
}
