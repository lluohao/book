package com.code.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * 购物车实体类
 * @author psn0183pp
 *
 */
public class Cart {
	private int userId;
	private List<BookItem> books;
	private Timestamp time;
	public List<BookItem> getBooks() {
		return books;
	}
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
