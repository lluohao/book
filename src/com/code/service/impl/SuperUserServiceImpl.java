package com.code.service.impl;

import com.code.dao.ISuperUserDAO;
import com.code.dao.fact.UserDaoFactory;
import com.code.entity.SuperUser;
import com.code.md5.MD5Encoding;
import com.code.service.ISuperUserService;

public class SuperUserServiceImpl implements ISuperUserService {

	private ISuperUserDAO dao = UserDaoFactory.getInstance().newISuperUserDAO();

	public int findSuperUser(String name, String password) {
		if (name == null || name.length() <= 1 || name.length() > 20) {
			return -2;
		}
		if (password == null) {
			return -3;
		}
		password = password.toUpperCase();
		SuperUser superUser = dao.findSuperUser(name, password);
		if (superUser == null) {
			return -1;
		}
		return superUser.getId();
	}

	@Override
	public int addSuperUser(String name, String password) {
		if (name.length() < 2 || name.length() > 20) {
			return -2;
		}
		if (dao.findSuperUser(name) != null) {
			return -3;
		}
		password = MD5Encoding.encoding(password);
		SuperUser superUser = new SuperUser();
		superUser.setName(name);
		superUser.setPassword(password);
		return dao.addSuperUser(superUser);
	}

	@Override
	public boolean updatePassword(int superUserId, String password) {
		SuperUser superUser = dao.findSuperUserById(superUserId);
		password = MD5Encoding.encoding(password);
		superUser.setPassword(password);
		return dao.updateSuperUser(superUser);
	}

	@Override
	public SuperUser findSuperUser(String superUserName) {
		return dao.findSuperUser(superUserName);
	}

	@Override
	public SuperUser findSuperUser(int id) {
		return dao.findSuperUserById(id);
	}

}
