package com.bridgeit.TradeFinanceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TradeFinanceApp.model.CustomResponse;
import com.bridgeit.TradeFinanceApp.model.User;
import com.bridgeit.TradeFinanceApp.service.UserService;
import com.bridgeit.TradeFinanceApp.token.GenerateJWT;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public CustomResponse  registerUser(@RequestBody User user) {
		CustomResponse customResponse = new CustomResponse();
		try {
			userService.registration(user);
			customResponse.setMessage("User registered successfully");
			customResponse.setStatusCode(200);
			return customResponse;
		} catch (Exception e) {
			customResponse.setMessage("User could not be registered");
			customResponse.setStatusCode(500);
			return customResponse;
		}
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public CustomResponse  loginUser(@RequestBody User user) {
		CustomResponse customResponse = new CustomResponse();
		try {
			user = userService.login(user);
			if(user != null){
				System.out.println("User deatils "+user);
				String jwt = GenerateJWT.generate(user.getId());
				customResponse.setMessage(jwt);
				customResponse.setStatusCode(200);
				return customResponse;
			} else {
				customResponse.setMessage("Invalid Details");
				customResponse.setStatusCode(500);
				return customResponse;
			}
		} catch (Exception e) {
			customResponse.setMessage("User from database is showing error");
			customResponse.setStatusCode(502);
			return customResponse;
		}
	}
	
}
