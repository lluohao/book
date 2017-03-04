package com.code.service.impl;

import com.code.dao.IRechargeDAO;
import com.code.dao.IUserDAO;
import com.code.dao.fact.DaoFactory;
import com.code.dao.fact.UserDaoFactory;
import com.code.entity.Recharge;
import com.code.entity.User;
import com.code.md5.MD5Encoding;
import com.code.service.IUserService;
import com.code.util.EmailUtil;

public class UserServiceImpl implements IUserService {

	private IUserDAO dao = UserDaoFactory.getInstance().newIUserDao();
	private IRechargeDAO recharDao = DaoFactory.newInstance().newRechargeDAO();

	public int findUser(String name, String password) {
		if (name == null || name.length() <= 1 || name.length() > 20) {
			return -2;
		}
		if (password == null) {
			return -3;
		}
		password = password.toUpperCase();
		User user = dao.findUser(name, password);
		if (user == null) {
			return -1;
		}
		return user.getId();
	}

	@Override
	public int addUser(String name, String password, String email) {
		if (name.length() < 2 || name.length() > 20
				|| !EmailUtil.isEmail(email)) {
			return -2;
		}
		if (dao.findUser(name) != null) {
			return -3;
		}
		if (dao.findUserByEmail(email) != null) {
			return -4;
		}
		password = MD5Encoding.encoding(password);
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		return dao.addUser(user);
	}

	@Override
	public boolean updatePassword(int userId, String password) {
		User user = dao.findUserById(userId);
		password = MD5Encoding.encoding(password);
		user.setPassword(password);
		return dao.updateUser(user);
	}

	@Override
	public boolean updateUser(int userId, int sex, int age, int status) {
		// 找到用户
		User user = dao.findUserById(userId);
		if (sex == 1) {
			user.setSex('男');
		} else {
			user.setSex('女');
		}
		if (age >= 0 && age <= 150) {
			user.setAge(age);
		} else {
			return false;
		}
		user.setStatus(status);
		return dao.updateUser(user);
	}

	@Override
	public boolean updateAccount(int userId, int count, int id) {
		// 找到用户
		User user = dao.findUserById(userId);
		user.setAccount(user.getAccount() + count);
		Recharge recharge = recharDao.findRechargeById(id);
		if (!recharge.isUse()) {
			// 记录未被使用提交更新
			return dao.updateUser(user);
		}
		return false;
	}

	@Override
	public User findUser(int id) {
		return dao.findUserById(id);
	}

	@Override
	public User findUser(String userName) {
		return dao.findUser(userName);
	}

	@Override
	public int updateUser(User user) {
		String name = user.getName();
		String email = user.getEmail();
		int account = user.getAccount();
		char sex = user.getSex();
		int age = user.getAge();
		int status = user.getStatus();
		if (name.length() < 2 || name.length() > 20) {
			return -2;
		}
		if (account < 0 || account > Integer.MAX_VALUE) {
			return -3;
		}
		if (!(age >= 0 && age <= 150)) {
			return -4;
		}
		if (!(status == 0 || status == 1)) {
			return -5;
		}
		if (!(sex == '男' || sex == '女')) {
			return -6;
		}
		if (!("".equals(email.trim()) || EmailUtil.isEmail(email))) {
			return -7;
		}
		if (dao.updateUser(user)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean removeUser(int userId) {
		return dao.removeUser(userId);
	}

	@Override
	public int addUser(User user) {
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		int account = user.getAccount();
		int age = user.getAge();
		char sex = user.getSex();
		int status = user.getStatus();

		if (dao.findUser(name) != null) {
			return -2;
		}
		if (account < 0 || account > Integer.MAX_VALUE) {
			return -3;
		}
		if (!(age >= 0 && age <= 150)) {
			return -4;
		}
		if (!(status == 0 || status == 1)) {
			return -5;
		}
		if (!(sex == '男' || sex == '女')) {
			return -6;
		}
		if (!("".equals(email) || EmailUtil.isEmail(email))) {
			return -7;
		}
		if (name.length() < 2 || name.length() > 20) {
			return -8;
		}
		if (!(password.length() >= 6 && password.length() <= 16)) {
			return -9;
		}
		password = MD5Encoding.encoding(password);
		return dao.addUser(user);
	}

}
