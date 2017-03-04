package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.code.dao.IBookDAO;
import com.code.dao.IShelfDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Book;
import com.code.entity.BookItem;
import com.code.entity.Shelf;
import com.code.jdbc.DBConnection;
import com.code.util.BookUtil;
import com.code.util.ShelfUtil;

public class ShelfDAOImpl implements IShelfDAO {

	@Override
	public int addShelf(int bookId, int userId) {
		int shelfId = -1;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into shelf(shelf_bookid,shelf_userid,shelf_time,shelf_price) values (?,?,?,?)";
		dbConn = new DBConnection();
		try {
			// 根据书的id 先找到书的信息
			IBookDAO dao = DaoFactory.newInstance().newBookDAO();
			Book book = dao.findBookById(bookId);

			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });

			pstmt.setInt(1, bookId);
			pstmt.setInt(2, userId);
			pstmt.setTimestamp(3, book.getTime());
			pstmt.setInt(4, book.getPrice() - book.getDiscount());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				shelfId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return shelfId;
	}

	@Override
	public boolean removeShelf(int bookId, int userId) {
		boolean boo = false;

		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from shelf where shelf_bookid=? and shelf_userid=?";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, userId);

			int count = pstmt.executeUpdate();
			boo = count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return boo;
	}

	public List<BookItem> findListById(int userId) {
		List<BookItem> list = new ArrayList<BookItem>();;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from book where book_id in (select shelf_bookid from shelf where shelf_userid=?)";
		dbConn = new DBConnection();
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			if (rs != null) {
				Book book = null;
				BookItem item = null;
				while (rs.next()) {
					book = BookUtil.parseBook(rs);
					item = new BookItem();
					item.setBook(book);
					item.setRealPrice(book.getPrice() - book.getDiscount());

					list.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public Shelf findShelfById(int userId) {
		Shelf shelf = new Shelf();
		shelf.setBooks(findListById(userId));
		shelf.setUserId(userId);
		// DBConnection dbConn = null;
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		// ResultSet rs = null;
		// String sql = "select * from shelf where shelf_userid=?";
		// dbConn = new DBConnection();
		// try {
		// conn = dbConn.getConnection();
		// pstmt = conn.prepareStatement(sql);
		// pstmt.setInt(1, userId);
		//
		// rs = pstmt.executeQuery();
		// if (rs != null && rs.next()) {
		// shelf = ShelfUtil.parseShelf(rs);
		// List<BookItem> bookItemList = findListById(userId);
		// shelf.setBooks(bookItemList);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// dbConn.close(conn, pstmt, rs);
		// }
		return shelf;
	}
}
