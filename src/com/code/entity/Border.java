package com.code.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单实体
 * @author psn0183pp
 *
 */

public class Border {
	
	private int id;
	private int userId;
	private int status;
	private Timestamp time;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public List<BookItem> getBooks() {
		return books;
	}
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}
	
	
}
