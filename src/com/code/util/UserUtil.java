package com.code.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.entity.User;

/**
 * 用户实体类
 * 
 * @author losthistory
 * 
 */
public class UserUtil {
	/**
	 * 从一个结果集中解析一个User对象
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setName(rs.getString("user_name"));
		user.setAccount(rs.getInt("user_account"));
		user.setAge(rs.getInt("user_age"));
		user.setPassword(rs.getString("user_password"));
		user.setEmail(rs.getString("user_email"));
		user.setStatus(rs.getInt("user_status"));
		user.setSex(rs.getInt("user_sex") == 0 ? '女' : '男');
		return user;
	}

}
