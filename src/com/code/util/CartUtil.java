package com.code.util;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.code.dao.IBookDAO;
import com.code.dao.ICartDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.entity.Cart;

public class CartUtil {
	/**
	 *  从resultset中解析出一个cart对象
	 * @param rs
	 * @return
	 * @throws Exception 
	 */
	public static Cart parseCart(ResultSet rs) throws Exception{
		Cart cart=new Cart();
		IBookDAO bookdao=DaoFactory.newInstance().newBookDAO();
		
		List<BookItem> lists=new ArrayList<BookItem>();
		lists=FindBooks.findBooks(rs.getInt("cart_userid"));
		
		cart.setBooks(lists);
		cart.setUserId(rs.getInt("cart_userid"));
		Date date=new Date();
		long time=date.getTime();
		Timestamp nowTime=new Timestamp(time);
		cart.setTime(nowTime);
		return cart;
	}
}
