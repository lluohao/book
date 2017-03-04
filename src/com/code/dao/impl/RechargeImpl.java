package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.dao.IRechargeDAO;
import com.code.entity.Recharge;
import com.code.jdbc.DBConnection;
import com.code.util.RechargeUtil;

public class RechargeImpl implements IRechargeDAO {

	@Override
	public int addRechargeService(Recharge recharge) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into recharge (recharge_id,recharge_userid,recharge_count,recharge_time,recharge_use) values(null,?,?,?,?)";
		int id = -1;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setInt(1, recharge.getUserId());
			pstmt.setInt(2, recharge.getCount());
			pstmt.setTimestamp(3, recharge.getTime());
			pstmt.setInt(4, recharge.isUse()?1:0);
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
	public Recharge findRechargeById(int id) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from recharge where recharge_id=?";
		Recharge recharge = null;
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				recharge=RechargeUtil.parseRecharge(rs);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return recharge;
	
	}
}
