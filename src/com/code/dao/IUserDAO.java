package com.code.dao;

import com.code.entity.User;

public interface IUserDAO {
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	int addUser(User user);

	/**
	 * 根据用户名和密码查找用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	User findUser(String name, String password);

	/**
	 * 根据用户查找用户
	 * 
	 * @param name
	 * @return
	 */
	User findUser(String name);

	/**
	 * 根据邮箱查找用户
	 * 
	 * @param email
	 * @return
	 */
	User findUserByEmail(String email);

	/**
	 * 更新用户信息
	 * 
	 * @param sex
	 * @param age
	 * @param status
	 * @return
	 */
	boolean updateUser(User user);
	/**
	 * 根据用户id查找用户
	 * @param userId
	 * @return
	 */
	User findUserById(int userId);
	
	boolean removeUser(int userId);

}
