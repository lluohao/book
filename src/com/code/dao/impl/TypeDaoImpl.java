package com.code.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.code.dao.ITypeDAO;
import com.code.entity.Type;
import com.code.jdbc.DBConnection;
import com.code.util.TypeUtil;

public class TypeDaoImpl implements ITypeDAO {
	private DBConnection dbConn = new DBConnection();
	Map<Integer,String> map=new HashMap<Integer,String>();

	public List<Type> allParentType() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select*from booktype where type_parent=-1";
		List<Type> types = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				types = new ArrayList<Type>();
				while (rs.next()) {
					types.add(TypeUtil.parserType(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return types;
	}

	public List<Type> allChildType(int parentId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select*from booktype where type_parent=?";
		List<Type> types = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parentId);
			rs = pstmt.executeQuery();
			if (rs != null) {
				types = new ArrayList<Type>();
				while (rs.next()) {
					types.add(TypeUtil.parserType(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return types;
	}

	@Override
	public Type parentType(int childType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select*from booktype where type_id=(select "
				+ "type_parent from booktype where type_id=?)";
		Type type = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, childType);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				type = TypeUtil.parserType(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return type;
	}

	public boolean isParentType(int typeId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select*from booktype where type_id=?";
		Type type = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				type = TypeUtil.parserType(rs);
				if (type.getParentTypeId() == -1) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return false;
	}

	public Type findByName(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select*from booktype where type_name=?";
		Type type = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				type = TypeUtil.parserType(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return type;
	}

	public int addType(Type type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into booktype values(null,?,?) ";
		int typeId = -1;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql, new int[] { 1 });
			pstmt.setString(1, type.getTypeName());
			pstmt.setInt(2, type.getParentTypeId());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				typeId = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return typeId;
	}

	public Type findById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select*from booktype where type_id=?";
		Type type = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				type = TypeUtil.parserType(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return type;
	}

	@Override
	public String findName(int id) {
		String name="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select type_name from booktype where type_id=?";
		Type type = null;
		try {
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				name=rs.getString("type_name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return name;
	}
	
	
	
}
