package com.code.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
	private String className;
	private String user;
	private String password;
	private String url;
	
	public DBConfig() {
		this.init();
	} 
	private void init() {
		Properties properties = new Properties();
		ClassLoader loader =DBConfig.class.getClassLoader();
		InputStream is = loader.getResourceAsStream("conf/db.properties");
		try {
			properties.load(is);
			this.className = properties.getProperty("className");
			this.user = properties.getProperty("user");
			this.password = properties.getProperty("password");
			this.url = properties.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClassName() {
		return className;
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	public String getUrl() {
		return url;
	}
	
}
