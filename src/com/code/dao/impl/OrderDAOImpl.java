package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.code.dao.IOrderDAO;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.entity.Border;
import com.code.jdbc.DBConnection;
import com.code.util.BorderUtil;

public class OrderDAOImpl implements IOrderDAO {
	/**
	 * 更改订单状态
	 * 
	 * @param status
	 * @return
	 */
	@Override
	public boolean updateStatus(int orderId, int status) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		Boolean bool=false;
		String sql="update border set border_status=? where border_id=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(2, orderId);
			pstmt.setInt(1, status);
			count=pstmt.executeUpdate();
			if (count>0) {
				bool=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return bool;
	}
	/**
	 * 添加订单
	 * 
	 * @return
	 */
	@Override
	public int addOrder(Border order) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int id=-1;
		String sql="insert into border (border_id,border_userid,border_status,border_time) values(null,?,?,?)";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql,new int[] {1});
			pstmt.setInt(1, order.getUserId());
			pstmt.setInt(2, order.getStatus());
			pstmt.setTimestamp(3, order.getTime());
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if (rs!=null&&rs.next()) {
				id=rs.getInt(1);
			}
			sql="insert into orderitem (orderitem_id,orderitem_borderid,orderitem_bookid,orderitem_price) values(null,?,?,?)";
			List<BookItem> list0=order.getBooks();
			pstmt=conn.prepareStatement(sql,new int[] {1});
			pstmt.setInt(1,id);
			for(BookItem book:list0){
				Book book0=book.getBook();
				pstmt.setInt(2, book0.getId());
				pstmt.setInt(3,223);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return id;
	}

	/**
	 * 根据订单号查找
	 * @param id
	 * @return
	 */
	@Override
	public Border findOrderById(int id) {
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Border border=null;
		String sql="select * from border where border_id=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if (rs != null && rs.next()) {
				border = BorderUtil.parseBorder(rs);
			}
		} catch (Exception e) {	
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return border;
	}
	
}
