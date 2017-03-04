package com.code.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.code.dao.IRechargeDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.Recharge;
import com.code.service.IRechargeService;

/**
 * 充值Service
 * 
 * @author psn0183pp
 * 
 */
public class RechargeServiceImpl implements IRechargeService {
	private IRechargeDAO dao = DaoFactory.newInstance().newRechargeDAO();

	@Override
	public int addRechargeService(int userId, int count, int id) {
		Recharge recharge=new Recharge();
		recharge.setUserId(userId);
		recharge.setCount(count);
		//获取当前时间
		Date date=new Date();
		long time=date.getTime();
		Timestamp nowTime=new Timestamp(time);
		recharge.setTime(nowTime);
		if (id == -1) {
			//支付记录有效，添加充值记录
			return dao.addRechargeService(recharge);
		}
		return -1;
	}

	@Override
	public Recharge findRechargeService(int id) {
		return dao.findRechargeById(id);		
	}

}
