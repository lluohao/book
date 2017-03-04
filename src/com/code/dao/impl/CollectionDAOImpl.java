package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.code.dao.ICollectionDAO;
import com.code.entity.Cart;
import com.code.entity.Collection;
import com.code.jdbc.DBConnection;
import com.code.util.CartUtil;
import com.code.util.CollectionUtil;

public class CollectionDAOImpl implements ICollectionDAO {
	/**
	 * 添加
	 * 
	 * @return
	 */
	@Override
	public int addCollection(int userId, int bookId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int id=-1;
		String sql="insert into collection (collection_userid,collection_bookid,collection_time,collection_id) values(?,?,?,null)";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql,new int[] {1});
			pstmt.setInt(1, userId);
			pstmt.setInt(2, bookId);
			Date date=new Date();
			long time=date.getTime();
			Timestamp nowTime=new Timestamp(time);
			pstmt.setTimestamp(3, nowTime);
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if (rs!=null&&rs.next()) {
				id=rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return id;
	}
	/**
	 * 移除
	 * @param bookId
	 * @param userId
	 * @return
	 */
	@Override
	public boolean removeCollection(int bookId, int userId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Boolean bool=false;
		String sql="delete from collection where collection_bookid=? and collection_userid=?";
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
	 * 根据userId查找collection
	 * @param userId
	 * @return
	 */
	@Override
	public Collection findCollectionByUserId(int userId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Collection collection=null;
		String sql="select * from collection where collection_userid=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				collection = CollectionUtil.parseCollection(rs);
			}
		} catch (Exception e) {	
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return collection;
	}
	@Override
	public boolean removeCollection(int[] bookIds, int userId) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Boolean bool=false;
		String idsStr = Arrays.toString(bookIds).replace("[", "(")
				.replace("]", ")");
		String sql="delete from collection where collection_bookid in "+idsStr+" and collection_userid=?";
//		System.out.println(sql);
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int count=pstmt.executeUpdate();
			bool=(count>0);
		} catch (Exception e) {	
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return bool;
	}

}
