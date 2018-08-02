package com.bridgeit.TradeFinanceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.TradeFinanceApp.dao.UserDao;
import com.bridgeit.TradeFinanceApp.model.Encryption;
import com.bridgeit.TradeFinanceApp.model.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	Encryption encryption;
	
	public int registration(User user) {
		String pwd = encryption.encryptPassword(user.getPassword());
		user.setPassword(pwd);
		return userDao.registration(user);
	}

	public User login(User user) {
		String pwd = encryption.encryptPassword(user.getPassword());
		user.setPassword(pwd);
		user.setEmail(user.getEmail());
		return userDao.login(user);
	}

	
	
}
