package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import com.code.dao.ICartDAO;
import com.code.entity.Cart;
import com.code.jdbc.DBConnection;
import com.code.util.CartUtil;

public class CartDAOImpl implements ICartDAO {
	/**
	 * 添加
	 * 
	 * @return
	 */
	@Override
	public int addBook(int bookId, int userId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int id=-1;
		String sql="insert into cart values(?,?,?)";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, bookId);
			Date date=new Date();
			long time=date.getTime();
			Timestamp nowTime=new Timestamp(time);
			pstmt.setTimestamp(3, nowTime);
			id=pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return id;
	}

	/**
	 * 从某人的购物车中移除一本书
	 * 
	 * @param bookId
	 * @param userId
	 * @return
	 */
	@Override
	public boolean removeBook(int bookId, int userId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Boolean bool=false;
		String sql="delete from cart where cart_bookid=? and cart_userid=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, userId);
			int count=pstmt.executeUpdate();
			bool=(count>0);
		} catch (Exception e) {	
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return bool;
	}
	/**
	 * 根据userId查找cart
	 * @param userId
	 * @return
	 */
	@Override
	public Cart findCartByUserId(int userId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Cart cart=null;
		String sql="select * from cart where cart_userid=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			if(rs!=null&&rs.next()){
			cart = CartUtil.parseCart(rs);
			}
		} catch (Exception e) {	
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return cart;
	}
	
}
