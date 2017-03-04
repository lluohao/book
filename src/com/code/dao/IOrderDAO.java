package com.code.dao;

import com.code.entity.Border;

public interface IOrderDAO {
	/**
	 * 更改订单状态
	 * 
	 * @param status
	 * @return
	 */
	boolean updateStatus(int orderId, int status);

	/**
	 * 添加订单
	 * 
	 * @return
	 */
	int addOrder(Border order);
	
	/**
	 * 根据订单号查找
	 * @param id
	 * @return
	 */
	Border findOrderById(int id);
}
