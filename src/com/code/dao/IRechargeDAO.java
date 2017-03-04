package com.code.dao;

import com.code.entity.Recharge;

public interface IRechargeDAO {
	/**
	 * 添加充值记录
	 * 
	 * @param userId
	 * @param count
	 * @param id
	 * @return
	 */
	int addRechargeService(Recharge recharge);
	
	/**
	 * 根据id找充值记录
	 * @param id   用户id
	 * @return
	 */
	Recharge findRechargeById(int id);
}
