package com.code.entity;

import java.sql.Timestamp;
/**
 * 书架、收藏、订单、购物车共用实体条类
 * @author psn0183pp
 *
 */

public class BookItem {
	private Book book;
	private int realPrice;
	private Timestamp time;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setRealPrice(int realPrice) {
		this.realPrice = realPrice;
	}
	public int getRealPrice() {
		return realPrice;
	}

	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
}
