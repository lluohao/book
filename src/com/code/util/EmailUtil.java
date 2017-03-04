package com.code.util;

/**
 * 邮箱工具类
 * 
 * @author losthistory
 * 
 */
public class EmailUtil {
	/**
	 * 判断一个字符串是否为邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		String regex = "\\w+@\\w+\\.\\w+";
		return email.matches(regex);
	}
}
