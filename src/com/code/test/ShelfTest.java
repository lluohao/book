package com.code.test;

import org.junit.Test;

import com.code.dao.IShelfDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Shelf;

public class ShelfTest {
	@Test
	public void testAdd(){
		IShelfDAO dao=DaoFactory.newInstance().newShelfDAO();
		int id=dao.addShelf(209, 100001);
		System.out.println(id);
		
	}
	@Test
	public void testRemove(){
		IShelfDAO dao=DaoFactory.newInstance().newShelfDAO();
		boolean boo=dao.removeShelf(210, 100001);
		System.out.println(boo);
		
	}
	@Test
	public void testFind(){
		IShelfDAO dao=DaoFactory.newInstance().newShelfDAO();
		Shelf shelf=dao.findShelfById(100001);
		System.out.println(shelf.getId());
		
	}

}
