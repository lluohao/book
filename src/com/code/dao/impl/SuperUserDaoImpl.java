package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.dao.ISuperUserDAO;
import com.code.entity.SuperUser;
import com.code.jdbc.DBConnection;
import com.code.util.SuperUserUtil;

public class SuperUserDaoImpl implements ISuperUserDAO {

	@Override
	public int addSuperUser(SuperUser superUser) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into SuperUser (SUser_id,SUser_name,SUser_password)values(null,?,?)";
		int id = -1;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, superUser.getName());
			pstmt.setString(2, superUser.getPassword());
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
	public SuperUser findSuperUser(String name, String password) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from SuperUser where SUser_name=? and SUser_password=?";
		SuperUser superUser = null;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				superUser = SuperUserUtil.parseSuperUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return superUser;
	}

	@Override
	public SuperUser findSuperUser(String name) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from SuperUser where SUser_name=?";
		SuperUser superUser = null;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				superUser = SuperUserUtil.parseSuperUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return superUser;
	}

	@Override
	public boolean updateSuperUser(SuperUser superUser) {
		boolean boo = false;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update SuperUser set SUser_name=?,SUser_password=? where SUser_id=?";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, superUser.getName());
			pstmt.setString(2, superUser.getPassword());
			pstmt.setInt(3, superUser.getId());

			int count = pstmt.executeUpdate();
			boo = count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return boo;
	}

	@Override
	public SuperUser findSuperUserById(int superUserId) {
		SuperUser superUser = null;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from SUser where SUser_id=?";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, superUserId);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				superUser = SuperUserUtil.parseSuperUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return superUser;
	}


}
