package com.code.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.entity.SuperUser;

public class SuperUserUtil {
	/**
	 * 从一个结果集中解析一个SuperUser对象
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static SuperUser parseSuperUser(ResultSet rs) throws SQLException {
		SuperUser superUser = new SuperUser();
		superUser.setId(rs.getInt("SUser_id"));
		superUser.setName(rs.getString("SUser_name"));
		superUser.setPassword(rs.getString("SUser_password"));
		return superUser;
	}

}
