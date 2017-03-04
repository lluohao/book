package com.code.service;

import com.code.entity.User;

public interface IUserService {
	int findUser(String name, String password);

	User findUser(int id);

	int addUser(String name, String password, String email);
	int addUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param password
	 * @param key
	 * @param code
	 * @return
	 */
	boolean updatePassword(int userId, String password);

	/**
	 * 更新用户信息
	 * 
	 * @param sex
	 * @param age
	 * @param status
	 * @return
	 */
	boolean updateUser(int userId, int sex, int age, int status);

	/**
	 * 充值
	 * 
	 * @param userId
	 * @param count
	 * @param id
	 * @return
	 */
	boolean updateAccount(int userId, int count, int id);
	
	
	User findUser(String userName);
	int updateUser(User user);
	boolean removeUser(int userId);
}
