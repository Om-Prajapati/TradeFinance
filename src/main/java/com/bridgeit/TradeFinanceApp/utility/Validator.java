package com.bridgeit.TradeFinanceApp.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.TradeFinanceApp.model.User;
import com.bridgeit.TradeFinanceApp.service.UserService;


@Component
public class Validator {

	@Autowired
	UserService userService;
	
	@Autowired
	Encryption encryption;
	
	public String validateSaveUser(User user) {
		
		String result="false";
		
		String userName="^[a-zA-Z]{2,}$";
		
		String emailFormat="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		String passwordFormat="^[a-zA-Z0-9]{8,}$";
		
		
		if(user.getUserName()==null||user.getUserName()==""){
			result="Your name is too short...";
			return result;
		}
		
		
		else if(user.getEmail()==null||user.getEmail()==""){
			result="Email is not in correct format.";
			return result;
		}
		
		else if(user.getPassword()==null||user.getPassword()==""){
			result="Password must contain words and number";
			return result;
		}
		
		else if(!user.getUserName().matches(userName)){
			result="Your  name is too short...";
			return result;
		}
		
		else if(!user.getEmail().matches(emailFormat)){
			result="Please enter a valid email address !!";
			return result;
		}
		
		else if(!user.getPassword().matches(passwordFormat)){
			result="Your password is short !!";
			return result;
		}
		
		else {
			User newUser= userService.emailValidation(user.getEmail());
			if (newUser != null) {
				result = "Email Id already exists";
				return result;
			} else {
				result = "success";
				return result;
			}
		}
	}

	public String validatePassword(String password) {
		String passwordFormat="^[a-zA-Z0-9]{8,}$";
		
		 if(!password.matches(passwordFormat)){
			 return "Your password is short !!";
			}
		 else{
			 return "true";
		 }
	}
	
}
