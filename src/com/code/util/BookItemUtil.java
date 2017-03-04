package com.code.util;

import java.sql.Timestamp;
import java.util.Date;

import com.code.entity.Book;
import com.code.entity.BookItem;

public class BookItemUtil {
	public static BookItem parseBookItem(Book book){
		BookItem bookItem=new BookItem();
		Date date=new Date();
		long time=date.getTime();
		Timestamp nowTime=new Timestamp(time);
		bookItem.setBook(book);
		bookItem.setRealPrice(book.getPrice()-book.getDiscount());
		bookItem.setTime(nowTime);
		return bookItem;
	}
}
 