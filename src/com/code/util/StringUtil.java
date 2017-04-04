package com.code.util;

/**
 * @author 罗浩
 * 
 **/
public class StringUtil {
	public static int safeToInteger(String str, int def) {
		try {
			int i = Integer.parseInt(str);
			return i;
		} catch (Exception e) {
			return def;
		}
	}
}
