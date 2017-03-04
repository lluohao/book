package com.code.util;

import java.sql.ResultSet;

import com.code.entity.Shelf;

public class ShelfUtil {
	
	public static Shelf parseShelf(ResultSet rs) throws Exception{
		Shelf shelf=new Shelf();
		shelf.setId(rs.getInt("shelf_id"));
		shelf.setUserId(rs.getInt("shelf_userid"));
		return shelf;
	}

}
