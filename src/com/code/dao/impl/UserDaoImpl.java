package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.dao.IUserDAO;
import com.code.entity.User;
import com.code.jdbc.DBConnection;
import com.code.util.UserUtil;

public class UserDaoImpl implements IUserDAO {

	@Override
	public int addUser(User user) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into user (user_id,user_name,user_password,user_age,user_sex,user_account,user_status,user_email) values(null,?,?,?,?,?,?,?)";
		int id = -1;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getAge());
			pstmt.setInt(4, user.getSex() == '女' ? 0 : 1);
			pstmt.setInt(5, user.getAccount());
			pstmt.setInt(6, user.getStatus());
			pstmt.setString(7, user.getEmail());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return id;
	}

	@Override
	public User findUser(String name, String password) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where user_name=? and user_password=?";
		User user = null;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user = UserUtil.parseUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public User findUser(String name) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where user_name=?";
		User user = null;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user = UserUtil.parseUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where user_email=?";
		User user = null;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user = UserUtil.parseUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean boo=false;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update user set user_name=?,user_password=?,user_age=?,user_sex=?,user_account=?,user_status=?,user_email=? where user_id=?";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getAge());
			pstmt.setInt(4, user.getSex() == '女' ? 0 : 1);
			pstmt.setInt(5, user.getAccount());
			pstmt.setInt(6, user.getStatus());
			pstmt.setString(7, user.getEmail());
			pstmt.setInt(8, user.getId());
			
			int count=pstmt.executeUpdate();
			boo=count>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return boo;
	}

	@Override
	public User findUserById(int userId) {
		User user=null;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where user_id=?";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				user=UserUtil.parseUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return user;
	}

	@Override
	public boolean removeUser(int userId) {
		boolean result=false;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from user where user_id=?";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int count=pstmt.executeUpdate();
			result=count>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return result;
	}

}
