package com.code.dao.impl;

import com.code.dao.ISuperUserDAO;
import com.code.dao.fact.UserDaoFactory;
import com.code.entity.SuperUser;
import com.code.md5.MD5Encoding;

public class AddSuperUser {

	public static void main(String[] args) {
		ISuperUserDAO suserDAO = UserDaoFactory.getInstance()
				.newISuperUserDAO();
		SuperUser superUser=new SuperUser();
		superUser.setName("lemengmneng");
		String password="lemm123457";
		password=MD5Encoding.encoding(password);
		superUser.setPassword(password);
		suserDAO.addSuperUser(superUser);

	}

}
