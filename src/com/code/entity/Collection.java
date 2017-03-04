package com.code.entity;

import java.util.List;
/**
 * 收藏实体类
 * @author psn0183pp
 *
 */

public class Collection {
	private int id;
	private int userID;
	private List<BookItem> books;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public List<BookItem> getBooks() {
		return books;
	}
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}
	
	
}
