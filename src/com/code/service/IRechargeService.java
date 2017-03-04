package com.code.service;

import com.code.entity.Recharge;

public interface IRechargeService {
	/**
	 * 添加充值记录
	 * 
	 * @param userId
	 * @param count
	 * @param id
	 * @return
	 */
	int addRechargeService(int userId, int count, int id);
	
	/**
	 * 根据id查找充值记录
	 * @param id
	 * @return
	 */
	Recharge findRechargeService(int id);

}
