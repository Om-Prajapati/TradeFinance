package com.bridgeit.TradeFinanceApp.dao;

import org.springframework.stereotype.Component;

import com.bridgeit.TradeFinanceApp.model.User;

@Component
public interface UserDao {

	public int registration(User user);
	
	public User login(User user);

}
