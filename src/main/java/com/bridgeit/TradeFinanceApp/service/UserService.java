package com.bridgeit.TradeFinanceApp.service;


import com.bridgeit.TradeFinanceApp.model.User;

public interface UserService {

	public int registration(User user);
	
	public User login(User user);
	
}
