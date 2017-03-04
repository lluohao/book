package com.code.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.entity.Book;

public class BookUtil {
	public static Book parseBook(ResultSet rs) throws SQLException{
	Book book = new Book();
	book.setId(rs.getInt("book_id"));
	book.setAuthor(rs.getString("book_author"));
	book.setContent(rs.getString("book_content"));
	book.setDiscount(rs.getInt("book_discount"));
	book.setDiscribe(rs.getString("book_discribe"));
	book.setImg(rs.getString("book_image"));
	book.setName(rs.getString("book_name"));
	book.setPrice(rs.getInt("book_price"));
	book.setSales(rs.getInt("book_sales"));
	book.setStatus(rs.getInt("book_status"));
	book.setStock(rs.getInt("book_stock"));
	book.setType(rs.getInt("book_type"));
	book.setTime(rs.getTimestamp("book_time"));
	return book;
	}
}
