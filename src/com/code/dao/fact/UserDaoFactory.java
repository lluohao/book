package com.code.dao.fact;

import com.code.dao.ISuperUserDAO;
import com.code.dao.IUserDAO;
import com.code.dao.impl.SuperUserDaoImpl;
import com.code.dao.impl.UserDaoImpl;

public class UserDaoFactory {
	private static UserDaoFactory instance = new UserDaoFactory();

	private UserDaoFactory() {

	}

	public static UserDaoFactory getInstance() {
		return instance;
	}

	public IUserDAO newIUserDao() {
		return new UserDaoImpl();
	}

	public ISuperUserDAO newISuperUserDAO() {
		return new SuperUserDaoImpl();
	}
}
