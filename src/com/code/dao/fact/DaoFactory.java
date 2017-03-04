package com.code.dao.fact;

import com.code.dao.IBookDAO;
import com.code.dao.ICartDAO;
import com.code.dao.ICollectionDAO;
import com.code.dao.IOrderDAO;
import com.code.dao.IRechargeDAO;
import com.code.dao.IShelfDAO;
import com.code.dao.ITypeDAO;
import com.code.dao.IUserDAO;
import com.code.dao.impl.BookDAOImpl;
import com.code.dao.impl.CartDAOImpl;
import com.code.dao.impl.CollectionDAOImpl;
import com.code.dao.impl.OrderDAOImpl;
import com.code.dao.impl.RechargeImpl;
import com.code.dao.impl.ShelfDAOImpl;
import com.code.dao.impl.TypeDaoImpl;
import com.code.dao.impl.UserDaoImpl;

public class DaoFactory {
	private static DaoFactory factory = new DaoFactory();

	public static DaoFactory newInstance() {
		return factory;
	}

	private DaoFactory() {
	}
	
	public static IUserDAO newUserDAO(){
		return new UserDaoImpl();
	}
	
	public IRechargeDAO newRechargeDAO(){
		return new RechargeImpl();
	}
	public ITypeDAO newTypeDAO(){
		return new TypeDaoImpl();
	}
	public IBookDAO newBookDAO() {
		return new BookDAOImpl();
	}

	public ICartDAO newCartDAO() {
		return new CartDAOImpl();
	}

	public ICollectionDAO newCollectionDAO() {
		return new CollectionDAOImpl();
	}

	public IOrderDAO newOrderDAO() {
		return new OrderDAOImpl();
	}

	public  IShelfDAO newShelfDAO() {
		return new ShelfDAOImpl();
	}
}
