package com.code.read;

import java.io.InputStream;

/**
 * @author LostHistory
 * 
 */
public class BookDate {
	private int from;
	private InputStream data;
	private String name;
	private int id;

	public InputStream getData() {
		return data;
	}

	public void setData(InputStream data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public BookDate(InputStream data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
