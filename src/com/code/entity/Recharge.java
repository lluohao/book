package com.code.entity;

import java.sql.Timestamp;
/**
 * 充值记录实体类
 * @author psn0183pp
 *
 */

public class Recharge {
	private int id;
	private int userId;
	private int count;
	private boolean use;
	public boolean isUse() {
		return use;
	}
	public void setUse(boolean use) {
		this.use = use;
	}
	private Timestamp time; 
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
