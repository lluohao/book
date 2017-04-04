package com.code.service.impl;

import com.code.service.ITypeService;
import com.code.service.fact.ServiceFactory;

public class MyTest {
	public static void main(String[] args) {
//		ICartService service = ServiceFactory.getInstance().newCartService();
//		System.out.println(service.findCart(100001).getBooks().get(0).getBook()
//				.getName());
		ITypeService service = ServiceFactory.getInstance().newTypeService();
		System.out.println(service.getLocation("book",20));
	}
}
