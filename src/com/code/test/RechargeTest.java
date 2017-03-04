package com.code.test;

import java.sql.Timestamp;

import org.junit.Test;

import com.code.dao.IRechargeDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Recharge;

public class RechargeTest {
	@Test
	public void testAdd(){
		Recharge r=new Recharge();
		r.setCount(100);
		r.setId(120);
		Timestamp t=null;
		r.setTime(t);
		r.setUserId(100002);
		IRechargeDAO dao=DaoFactory.newInstance().newRechargeDAO();
		int id=dao.addRechargeService(r);
		System.out.println(id);
		
	}
	@Test
	public void testFind(){
		IRechargeDAO dao=DaoFactory.newInstance().newRechargeDAO();
		Recharge r=dao.findRechargeById(100001);
		System.out.println(r.getCount());
	}
}
