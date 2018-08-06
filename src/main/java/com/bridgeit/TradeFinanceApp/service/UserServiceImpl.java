package com.bridgeit.TradeFinanceApp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.TradeFinanceApp.dao.UserDao;
import com.bridgeit.TradeFinanceApp.model.User;
import com.bridgeit.TradeFinanceApp.utility.Encryption;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	Encryption encryption;
	
	public int registration(User user) {
		
		String pwd = encryption.encryptPassword(user.getPassword());
		user.setPassword(pwd);
		user.setActive(false);
		
		String uniqueID = UUID.randomUUID().toString();
		user.setAuthenticate_user_key(uniqueID);
		
		Date date = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		user.setCurrentDate(currentTime);
		
		
		return userDao.registration(user);
		
	}

	public User login(User user) {
		String pwd = encryption.encryptPassword(user.getPassword());
		user.setPassword(pwd);
		user.setEmail(user.getEmail());
		return userDao.login(user);
	}

	public User updateUser(User user) {
		Date date = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifyDate = sdf.format(date);
		user.setModificationDate(modifyDate);
		return userDao.updateUser(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public User getUserByAuthenticateKey(String authenticateKey) {
		
		return userDao.getUserByAuthenticateKey(authenticateKey);
	}

	public User emailValidation(String email) {
		return userDao.emailValidation(email);
	}


}
