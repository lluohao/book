package com.code.test;

import org.junit.Test;

import com.code.service.IOrderService;
import com.code.service.fact.ServiceFactory;

public class IOrderTest {
	@Test
	public void tesAddOrder() throws Exception {
		IOrderService service = ServiceFactory.getInstance().newOrderService();
		service.addOrder(new int[]{209,210,211}, 100002);
	}
}
