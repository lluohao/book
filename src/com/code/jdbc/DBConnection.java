package com.code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static DBConfig config;
	static{
		config = new DBConfig();
		try {
			Class.forName(config.getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		Connection conn = null;
		String url = config.getUrl();
		String user = config.getUser();
		String password = config.getPassword();
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public void close(Connection conn,Statement sttm,ResultSet set){
		try {
			if(conn!=null)conn.close();
			if(sttm!=null)sttm.close();
			if(set!=null)set.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
