package com.code.util;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.code.dao.IBookDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.BookItem;
import com.code.entity.Border;

public class BorderUtil {
	/**
	 *  从resultset中解析出一个cart对象
	 * @param rs
	 * @return
	 * @throws Exception 
	 */
	public static Border parseBorder(ResultSet rs) throws Exception{
		Border myOrder=new Border();
		IBookDAO bookdao=DaoFactory.newInstance().newBookDAO();
		List<BookItem> lists=new ArrayList<BookItem>();
		
		lists=FindBooks.findOrderBooks(rs.getInt("border_id"));
		myOrder.setBooks(lists);
		myOrder.setId(rs.getInt("border_id"));
		myOrder.setBooks(lists);
		Date date=new Date();
		long time=date.getTime();
		Timestamp nowTime=new Timestamp(time);
		myOrder.setTime(nowTime);
		myOrder.setUserId(rs.getInt("border_userid"));
		myOrder.setStatus(rs.getInt("border_status"));
		return myOrder;
	}
}
