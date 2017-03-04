package com.code.entity;

import java.util.List;
/**
 * 书架实体
 * @author psn0183pp
 *
 */

public class Shelf {
	private int id;
	private int userId;
	private List<BookItem> books;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<BookItem> getBooks() {
		return books;
	}
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}

	
	
}
