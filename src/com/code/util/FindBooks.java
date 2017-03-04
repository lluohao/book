package com.code.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.code.dao.IBookDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.jdbc.DBConnection;

public class FindBooks {
	public static List<BookItem> findBooks(int userId){
		List<BookItem> list=new ArrayList<BookItem>();
		IBookDAO bookdao=DaoFactory.newInstance().newBookDAO();
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int id=-1;
		String sql="select cart_bookid from cart where cart_userid=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,userId);
			rs=pstmt.executeQuery();
			if (rs!=null) {
				List<Integer> list0=new ArrayList<Integer>();
				while(rs.next()){
					list0.add(rs.getInt("cart_bookid"));
				}
				int[] ids=new int[list0.size()];
				for (int i=0;i<list0.size();i++) {
					ids[i]=list0.get(i);
				}
				List<Book> listbook=bookdao.findBooksByIds(ids);
				for (Book book : listbook) {
					BookItem itme0=BookItemUtil.parseBookItem(book);
					list.add(itme0);
				}
				System.out.println(list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return list;
		
	}
	public static List<BookItem> findOrderBooks(int borderId){
		List<BookItem> list=new ArrayList<BookItem>();
		IBookDAO bookdao=DaoFactory.newInstance().newBookDAO();
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select orderitem_bookid from orderitem where orderItem_borderid=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,borderId);
			rs=pstmt.executeQuery();
			if (rs!=null) {
				List<Integer> list0=new ArrayList<Integer>();
				while(rs.next()){
					list0.add(rs.getInt("orderitem_bookid"));
				}
				int[] ids=new int[list0.size()];
				for (int i=0;i<list0.size();i++) {
					ids[i]=list0.get(i);
				}
				List<Book> listbook=bookdao.findBooksByIds(ids);
				for (Book book : listbook) {
					BookItem itme0=BookItemUtil.parseBookItem(book);
					list.add(itme0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return list;
		
	}
	public static List<BookItem> findCollBooks(int userId){
		List<BookItem> list=new ArrayList<BookItem>();
		IBookDAO bookdao=DaoFactory.newInstance().newBookDAO();
		DBConnection dbconn=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int id=-1;
		String sql="select collection_bookid from collection where collection_userid=?";
		try {
			dbconn=new DBConnection();
			conn=dbconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,userId);
			rs=pstmt.executeQuery();
			if (rs!=null) {
				List<Integer> list0=new ArrayList<Integer>();
				while(rs.next()){
					list0.add(rs.getInt("collection_bookid"));
				}
				int[] ids=new int[list0.size()];
				for (int i=0;i<list0.size();i++) {
					ids[i]=list0.get(i);
				}
				List<Book> listbook=bookdao.findBooksByIds(ids);
				for (Book book : listbook) {
					BookItem itme0=BookItemUtil.parseBookItem(book);
					list.add(itme0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			dbconn.close(conn, pstmt, rs);
		}
		return list;
		
	}
	@Test
	public void test(){
		System.out.println(findBooks(100001).get(0).getBook().getName());
	}
}
