package com.bridgeit.TradeFinanceApp.service;


import com.bridgeit.TradeFinanceApp.model.User;

public interface UserService {

	public int registration(User user);
	
	public User login(User user);
	
	public User updateUser(User user);
	
	public User getUserById(int id);
	
	public User getUserByAuthenticateKey(String authenticateKey);
	
	public User emailValidation(String email);
	
}
