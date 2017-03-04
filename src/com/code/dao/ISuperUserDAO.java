package com.code.dao;

import com.code.entity.SuperUser;

public interface ISuperUserDAO {
	/**
	 * 添加用户
	 * 
	 * @param SuperUser
	 * @return
	 */
	int addSuperUser(SuperUser SuperUser);

	/**
	 * 根据用户名和密码查找用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	SuperUser findSuperUser(String name, String password);

	/**
	 * 根据用户查找用户
	 * 
	 * @param name
	 * @return
	 */
	SuperUser findSuperUser(String name);


	/**
	 * 更新用户信息
	 * 
	 * @param sex
	 * @param age
	 * @param status
	 * @return
	 */
	boolean updateSuperUser(SuperUser SuperUser);
	/**
	 * 根据用户id查找用户
	 * @param SuperUserId
	 * @return
	 */
	SuperUser findSuperUserById(int SuperUserId);
	
}
