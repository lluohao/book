package com.code.dao.fact;

import com.code.service.ISuperUserService;
import com.code.service.IUserService;
import com.code.service.impl.SuperUserServiceImpl;
import com.code.service.impl.UserServiceImpl;

public class UserServcieFactory {
	private static UserServcieFactory instance = new UserServcieFactory();

	private UserServcieFactory() {

	}

	public static UserServcieFactory getInstance() {
		return instance;
	}

	public IUserService newUserService() {
		return new UserServiceImpl();
	}
	public ISuperUserService newSuperUserService() {
		return new SuperUserServiceImpl();
	}
}
