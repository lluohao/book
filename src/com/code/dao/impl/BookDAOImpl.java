package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.code.dao.IBookDAO;
import com.code.dao.ITypeDAO;
import com.code.entity.Book;
import com.code.entity.Type;
import com.code.jdbc.DBConnection;
import com.code.util.BookUtil;

public class BookDAOImpl implements IBookDAO {
	private ITypeDAO typeDAO = new TypeDaoImpl();

	@Override
	public List<Book> hotBooks(int num, int from) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book order by book_sales desc limit ?,?";
		List<Book> books = new ArrayList<Book>();
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, from - 1);
			psttm.setInt(2, num);
			rs = psttm.executeQuery();
			while (rs != null && rs.next()) {
				books.add(BookUtil.parseBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return books;
	}

	@Override
	public List<Book> newBooks(int num, int from) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book order by book_time desc limit ?,?";
		List<Book> books = new ArrayList<Book>();
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, from - 1);
			psttm.setInt(2, num);
			rs = psttm.executeQuery();
			while (rs != null && rs.next()) {
				books.add(BookUtil.parseBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return books;
	}

	@Override
	public List<Book> newBooks(int num, int from, int typeId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book where book_type=? order by book_time desc limit ?,?";
		if (typeId == -1) {
			sql = "select * from book where ?=-1 order by book_time desc limit ?,?";
		} else if (typeDAO.isParentType(typeId)) {
			sql = sql
					.replace("book_type=?",
							"book_type in (select type_id from booktype where type_parent=?)");
		}
		List<Book> books = new ArrayList<Book>();
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, typeId);
			psttm.setInt(2, from - 1);
			psttm.setInt(3, num);
			rs = psttm.executeQuery();
			while (rs != null && rs.next()) {
				books.add(BookUtil.parseBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return books;
	}

	@Override
	public List<Book> hotBooks(int num, int from, int typeId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book where book_type=? order by book_sales desc limit ?,?";
		if (typeDAO.isParentType(typeId)) {
			sql = sql
					.replace("book_type=?",
							"book_type in (select type_id from booktype where type_parent=?)");
		}
		List<Book> books = new ArrayList<Book>();
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, typeId);
			psttm.setInt(2, from - 1);
			psttm.setInt(3, num);
			rs = psttm.executeQuery();
			while (rs != null && rs.next()) {
				books.add(BookUtil.parseBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return books;
	}

	@Override
	public List<Book> searchByPrice(int typeId, int minPrice, int maxPrice,
			int pageNo, int pageCount, int orderType) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book where book_type=? and book_price>=? and book_price<=? ORDERBYSTR limit ?,? ";
		if (orderType == -1) {
			sql = sql.replace("ORDERBYSTR", "order by book_price desc");
		} else {
			sql = sql.replace("ORDERBYSTR", "order by book_price");
		}
		if (typeDAO.isParentType(typeId)) {
			sql = sql
					.replace("book_type=?",
							"book_type in (select type_id from booktype where type_parent=?)");
		}
		// select * from book where book_type in (select type_id from booktype
		// where type_parent=?) and book_price>=? and book_price<=? order by
		// book_price desc limit ?,?
		List<Book> books = new ArrayList<Book>();
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, typeId);
			psttm.setInt(2, minPrice);
			psttm.setInt(3, maxPrice);
			psttm.setInt(4, (pageNo - 1) * pageCount);
			psttm.setInt(5, pageCount);
			rs = psttm.executeQuery();
			while (rs != null && rs.next()) {
				books.add(BookUtil.parseBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return books;
	}

	@Override
	public Book findBookById(int id) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book where book_id=?";
		Book book = null;
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, id);
			rs = psttm.executeQuery();
			while (rs != null && rs.next()) {
				book = BookUtil.parseBook(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return book;
	}

	@Override
	public List<Book> findBooksByIds(int[] ids) {
		if (ids == null || ids.length == 0) {
			return new ArrayList<Book>();
		}
		List<Book> books = new ArrayList<Book>();
		for (int id : ids) {
			Book book = findBookById(id);
			if (book != null) {
				books.add(book);
			}
		}
		return books;
	}

	@Override
	public int minBookId() {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select min(book_id) from book";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			rs = psttm.executeQuery();
			if (rs != null && rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return 0;
	}

	@Override
	public int maxBookId() {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select max(book_id) from book";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			rs = psttm.executeQuery();
			if (rs != null && rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return 0;
	}

	/**
	 * 计算某类型的书的数量
	 */
	public int countType(int type) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select count(*) from book where book_type=?";
		if (typeDAO.isParentType(type)) {
			sql = "select count(*) from book where book_type in (select type_id from booktype where type_parent=?)";
		}
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, type);
			rs = psttm.executeQuery();
			if (rs != null && rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return 0;
	}

	/**
	 * 统计所有书的数量
	 */
	public int count() {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select count(*) from book";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			rs = psttm.executeQuery();
			if (rs != null && rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return 0;
	}

	@Override
	public int addBook(Book book) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		int id = -1;
		String sql = "insert into book (book_id,book_name,book_price,book_content,book_status,"
				+ "book_stock,book_author,book_image,book_type,book_discribe,book_sales,book_discount,book_time)"
				+ "value(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql, new int[] { 1 });
			psttm.setString(1, book.getName());
			psttm.setInt(2, book.getPrice());
			psttm.setString(3, book.getContent());
			psttm.setInt(4, book.getStatus());
			psttm.setInt(5, book.getStock());
			psttm.setString(6, book.getAuthor());
			psttm.setString(7, book.getImg());
			psttm.setInt(8, book.getType());
			psttm.setString(9, book.getDiscribe());
			psttm.setInt(10, book.getSales());
			psttm.setInt(11, book.getDiscount());
			psttm.setTimestamp(12, book.getTime());
			psttm.executeUpdate();
			rs = psttm.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return id;
	}

	@Override
	public int countType(int type, int minPrice, int maxPrice) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select count(*) from book where book_type=? and book_price>=? and book_price<=?";
		if (typeDAO.isParentType(type)) {
			sql = "select count(*) from book where book_type in (select type_id from booktype where type_parent=?) and book_price>=? and book_price<=?";
		}
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, type);
			psttm.setInt(2, minPrice);
			psttm.setInt(3, maxPrice);
			rs = psttm.executeQuery();
			if (rs != null && rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return 0;
	}

	public List<Book> searchBooks(String keyCode, int num, int from) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		List<Book> books = null;
		String sql = "SELECT*FROM book WHERE book_name LIKE '%" + keyCode
				+ "%' " + "OR book_author LIKE '%" + keyCode
				+ "%' OR book_discribe LIKE '%" + keyCode
				+ "%' order by book_id limit ?,?";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setInt(1, from - 1);
			psttm.setInt(2, num);
			rs = psttm.executeQuery();
			if (rs != null) {
				books = new ArrayList<Book>();
				while (rs.next()) {
					books.add(BookUtil.parseBook(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return books;
	}

	public int searchBookCounts(String keyCode) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		int counts = 0;
		String sql = "SELECT count(*) FROM book WHERE book_name LIKE '%"
				+ keyCode + "%' " + "OR book_author LIKE '%" + keyCode
				+ "%' OR book_discribe LIKE '%" + keyCode
				+ "%' order by book_id";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			rs = psttm.executeQuery();
			if (rs != null && rs.next()) {
				counts = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return counts;
	}

	@Override
	public boolean updateBook(Book book) {
		boolean result = false;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update book set book_name=?,book_author=?,book_content=?,book_price=?,book_type=?,book_sales=?,book_stock=?,"
				+ "book_time=?,book_image=?,book_discribe=?,book_discount=? where book_id=?";
		try {
			dbConn = new DBConnection();
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getContent());
			pstmt.setInt(4, book.getPrice());
			pstmt.setInt(5, book.getType());
			pstmt.setInt(6, book.getSales());
			pstmt.setInt(7, book.getStock());
			pstmt.setTimestamp(8, book.getTime());
			pstmt.setString(9, book.getImg());
			pstmt.setString(10, book.getDiscribe());
			pstmt.setInt(11, book.getDiscount());
			pstmt.setInt(12, book.getId());

			int count = pstmt.executeUpdate();
			result = count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return result;
	}

	@Override
	public Book findBooksByKey(String bookName) {
		Book book = null;
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement psttm = null;
		ResultSet rs = null;
		String sql = "select * from book where book_name=?";
		try {
			conn = dbConn.getConnection();
			psttm = conn.prepareStatement(sql);
			psttm.setString(1, bookName);
			rs = psttm.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					book = BookUtil.parseBook(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, psttm, rs);
		}
		return book;
	}

}
