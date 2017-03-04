package com.code.service;

import com.code.entity.SuperUser;

public interface ISuperUserService {
	int findSuperUser(String name, String password);

	SuperUser findSuperUser(int id);

	int addSuperUser(String name, String password);

	/**
	 * 修改密码
	 * 
	 * @param SuperUserId
	 * @param password
	 * @param key
	 * @param code
	 * @return
	 */
	boolean updatePassword(int SuperUserId, String password);

	SuperUser findSuperUser(String SuperUserName);
}
