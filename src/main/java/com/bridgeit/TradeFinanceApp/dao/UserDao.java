package com.bridgeit.TradeFinanceApp.dao;

import org.springframework.stereotype.Component;

import com.bridgeit.TradeFinanceApp.model.User;

@Component
public interface UserDao {

	public int registration(User user);
	
	public User login(User user);

	public User updateUser(User user);
	
	public User getUserById(int id);
	
	public User getUserByAuthenticateKey(String authenticateKey);
	
	public User emailValidation(String email);
	
}
