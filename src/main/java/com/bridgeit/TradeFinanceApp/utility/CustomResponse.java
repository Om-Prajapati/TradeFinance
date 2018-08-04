package com.bridgeit.TradeFinanceApp.utility;

import com.bridgeit.TradeFinanceApp.model.User;

public class CustomResponse {

	private String message;

	private int statusCode;

	private User user;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
