package com.code.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 路径处理工具类
 * 
 * @author losthistory
 * 
 */
public class PathUtil {
	/**
	 * 从配置文件中加载根目录
	 */
	private static String basePath = null;
	private static String baseStr = "root";
	static {
		Properties pro = new Properties();
		try {
			pro.load(PathUtil.class
					.getResourceAsStream("/conf/server.properties"));
			basePath = (String) pro.get("root");
		} catch (IOException e) {
			e.printStackTrace();
			basePath = "F:/date";
		}
	}

	/**
	 * 将相对路径转化为绝对路径
	 * 
	 * @param rePath
	 * @return
	 */
	public static String toAbsPath(String rePath) {
		return rePath.replace(baseStr, basePath);
	}

	/**
	 * 将绝对路径转化为相对路径
	 * 
	 * @param absPath
	 * @return
	 */
	public static String toRePath(String absPath) {
		return absPath.replace(basePath, baseStr);
	}
	
	public static void main(String[] args) {
		System.out.println(PathUtil.toRePath("F:\\data\\book\\xh"));
	}
}
