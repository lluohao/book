package com.code.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.code.dao.IBookDAO;
import com.code.dao.IOrderDAO;
import com.code.dao.fact.DaoFactory;
import com.code.entity.BookItem;
import com.code.entity.Border;
import com.code.service.IOrderService;

/**
 * 订单Service
 * 
 * @author psn0183pp
 * 
 */

public class OrderServiceImpl implements IOrderService {
	private IOrderDAO oDao = DaoFactory.newInstance().newOrderDAO();
	private IBookDAO bDao = DaoFactory.newInstance().newBookDAO();

	@Override
	public boolean updateStatus(int orderId, int status, int id) {
		if (id == -1) {
			// 支付成功，修改订单状态
			return oDao.updateStatus(orderId, status);
		}
		return false;
	}

	@Override
	public int addOrder(int[] ids, int userId) {
		Border order = new Border();
		// 获取当前时间
		Date date = new Date();
		long time = date.getTime();
		Timestamp nowTime = new Timestamp(time);
		order.setTime(nowTime);
		// 设置订单的用户id
		order.setUserId(userId);
		List<BookItem> bookItems = new ArrayList<BookItem>();
		for (int i = 0; i < ids.length; i++) {
			BookItem bi = new BookItem();
			bi.setBook(bDao.findBookById(ids[i]));
			// 设置实际价格
			bi.setRealPrice(bi.getBook().getPrice()
					- bi.getBook().getDiscount());
			bookItems.add(bi);
		}
		order.setBooks(bookItems);
		return oDao.addOrder(order);

	}

	@Override
	public Border findOrder(int id) {
		return oDao.findOrderById(id);
	}

}
