package com.code.service;

import com.code.entity.Border;

public interface IOrderService {
	/**
	 * 更改订单状态
	 * 
	 * @param status
	 * @return
	 */
	boolean updateStatus(int orderId,int status, int id);

	/**
	 * 添加订单
	 * 
	 * @param ids
	 * @param userId
	 * @return
	 */
	int addOrder(int[] ids, int userId);
	
	Border findOrder(int id); 
}
