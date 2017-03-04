package com.code.test;


import org.junit.Test;

import com.code.dao.IUserDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.User;

public class UserTest {
	@Test
	public void testAddUser() throws Exception {

		IUserDAO dao = DaoFactory.newInstance().newUserDAO();

		User user = new User();
		user.setName("llh");
		user.setPassword("sttm123457");
		user.setEmail("840230057@qq.com");
		System.out.println(dao.addUser(user));
	}
	@Test
	public void testFindUser() throws Exception {

		IUserDAO dao = DaoFactory.newInstance().newUserDAO();
//		System.out.println(dao.findUser("llh", "sttm123457"));
//		System.out.println(dao.findUserByEmail("942258070@qq.com"));
		System.out.println(dao.findUserById(100001).getName());
	}
	@Test	
	public void testUpdateUser(){
		User user=new User();
		user.setAccount(10025);
		user.setAge(20);
		user.setEmail("33333");
		user.setId(100001);
		user.setName("testName");
		user.setPassword("zzzzz");
		user.setSex('男');
		user.setStatus(1);
		
		IUserDAO dao = DaoFactory.newInstance().newUserDAO();
		
		boolean b=dao.updateUser(user);
		System.out.println(b);
		
	}
}
