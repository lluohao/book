package com.code.entity;

/**
 * 超级用户实体类
 * 
 */
public class SuperUser {
	@Override
	public String toString() {
		return "SuperUser [id=" + id + ", name=" + name + ", password="
				+ password + "]";
	}

	private int id;
	private String name;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
