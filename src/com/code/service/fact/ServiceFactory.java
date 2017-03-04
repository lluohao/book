package com.code.service.fact;

import com.code.service.IBookService;
import com.code.service.ICartService;
import com.code.service.ICollectionService;
import com.code.service.INetBookService;
import com.code.service.IOrderService;
import com.code.service.IRechargeService;
import com.code.service.IShelfService;
import com.code.service.ISuperUserService;
import com.code.service.ITypeService;
import com.code.service.IUserService;
import com.code.service.impl.BookServiceImpl;
import com.code.service.impl.CartServiceImpl;
import com.code.service.impl.CollectionServiceImpl;
import com.code.service.impl.NetBookServiceImpl;
import com.code.service.impl.OrderServiceImpl;
import com.code.service.impl.RechargeServiceImpl;
import com.code.service.impl.ShelfServiceImpl;
import com.code.service.impl.SuperUserServiceImpl;
import com.code.service.impl.TypeServiceImpl;
import com.code.service.impl.UserServiceImpl;

/**
 * 业务层工厂类
 *
 */
public class ServiceFactory {
	private static ServiceFactory instance = new ServiceFactory();

	public static ServiceFactory getInstance() {
		return instance;
	}

	private ServiceFactory() {
	}

	public IBookService newBookService() {
		return new BookServiceImpl();
	}

	public ICartService newCartService() {
		return new CartServiceImpl();
	}

	public ICollectionService newCollectionService() {
		return new CollectionServiceImpl();
	}

	public ITypeService newTypeService() {
		return new TypeServiceImpl();
	}

	public  IOrderService newOrderService() {
		return new OrderServiceImpl();
	}

	public  IRechargeService newRechargeService() {
		return new RechargeServiceImpl();
	}

	public  IShelfService newShelfService() {
		return new ShelfServiceImpl();
	}

	public  IUserService newUserService() {
		return new UserServiceImpl();
	}
	
	public  ISuperUserService newSuperUserService() {
		return new SuperUserServiceImpl();
	}
	//root/image/1.jpg
	public INetBookService newNetBookservice(){
		return new NetBookServiceImpl();
	}
}
