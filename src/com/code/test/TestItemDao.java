package com.code.test;

import java.sql.Timestamp;

import org.junit.Test;

import com.code.dao.ICartDAO;
import com.code.dao.ICollectionDAO;
import com.code.dao.IOrderDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Border;
import com.code.entity.Cart;
import com.code.entity.Collection;

public class TestItemDao {
/*	@Test
	public void testAddcart(){
		ICartDAO impl=DaoFactory.newInstance().newCartDAO();
		for(int i=0;i<5;i++){
		impl.addBook(460+i,100000);
		}
	}*/
/*	@Test
	public void testremovecart(){
		ICartDAO impl=DaoFactory.newInstance().newCartDAO();
		boolean bool=impl.removeBook(236, 100001);
		System.out.println(bool);
	}*/
/*	@Test
	public void testfind(){
		ICollectionDAO impl=DaoFactory.newInstance().newCollectionDAO();
		Collection coll=impl.findCollectionByUserId(100001);
		System.out.println(coll.getBooks().get(1).getBook().getName());
	}*/
	@Test
	public void testAddColl(){
		ICollectionDAO impl=DaoFactory.newInstance().newCollectionDAO();
		int id=impl.addCollection(100002,288);
		System.out.println(id);
	}
/*@Test
	public void testAddorder(){
		IOrderDAO impl=DaoFactory.newInstance().newOrderDAO();
		Border order=new Border();
		Timestamp t=null;
		order.setTime(t);
		order.setStatus(1);
		order.setUserId(100001);
		int id=impl.addOrder(order);
		System.out.println(id);
	}*/
/*	@Test
	public void testremovecoll(){
		ICollectionDAO impl=DaoFactory.newInstance().newCollectionDAO();
		boolean bool=impl.removeCollection(2, 100002);
		System.out.println(bool);
	}*/
	/*@Test
	public void testremoveborder(){
		IOrderDAO impl=DaoFactory.newInstance().newOrderDAO();
		boolean bool=impl.updateStatus(7, 2);
		System.out.println(bool);
	}*/
/*	@Test
	public void testfindCart(){
		ICartDAO impl=DaoFactory.newInstance().newCartDAO();
		Cart cart=impl.findCartByUserId(100001);
		System.out.println(cart.getBooks().get(1).getBook().getName());
	}*/
	
}
