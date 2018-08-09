package com.bridgeit.TradeFinanceApp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TradeFinanceApp.utility.CustomResponse;
import com.bridgeit.TradeFinanceApp.utility.Encryption;
import com.bridgeit.TradeFinanceApp.utility.SendMail;
import com.bridgeit.TradeFinanceApp.utility.Validator;
import com.bridgeit.TradeFinanceApp.model.User;
import com.bridgeit.TradeFinanceApp.service.UserService;
import com.bridgeit.TradeFinanceApp.token.GenerateJWT;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	SendMail sendmail;
	
	@Autowired
	Validator validator;
	
	@Autowired
	Encryption encryption;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public CustomResponse  registerUser(@RequestBody User user ,HttpServletRequest request) {
		CustomResponse customResponse = new CustomResponse();
		String isValid = validator.validateSaveUser(user);
		if(isValid.equals("success")) {
			try {
				int id=userService.registration(user);
				if(id != 0) {
					String url = request.getRequestURL().toString();
					url = url.substring(0, url.lastIndexOf("/")) + "/" + "verifyMail/" + user.getAuthenticate_user_key();
					sendmail.sendMail("tradefinancebridgelabz@gmail.com", user.getEmail(), "Welcome to bridgelabz", url);
					customResponse.setMessage("User registered successfully please check your mail and active your account");
					customResponse.setStatusCode(200);
				}else {
					customResponse.setMessage("User could not be registered");
					customResponse.setStatusCode(502);
				}
			} catch (Exception e) {
				e.printStackTrace();
				customResponse.setMessage("Exception");
				customResponse.setStatusCode(500);
			}
		} else {
			customResponse.setMessage(isValid);
			customResponse.setStatusCode(502);
		}
		return customResponse;
	}
	
	@RequestMapping(value = "/verifyMail/{authenticate_user_key:.+}", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> verifyMail(@PathVariable("authenticate_user_key") String authenticate_user_key, HttpServletResponse response) throws IOException {
		CustomResponse customResponse = new CustomResponse();
		User user = null;
		try {
			user= userService.getUserByAuthenticateKey(authenticate_user_key);
			if(user.isActive()==false){
				user.setActive(true);
				userService.updateUser(user);
				customResponse.setMessage("User is active");
				customResponse.setStatusCode(200);
				response.sendRedirect("http://localhost:8080/TradeFinance/#!/login");
				return new ResponseEntity<CustomResponse>(customResponse , HttpStatus.OK);
			}else {
				customResponse.setMessage("User is already activate");
				customResponse.setStatusCode(500);
				return new ResponseEntity<CustomResponse>(customResponse , HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			customResponse.setMessage("User details fails");
			customResponse.setStatusCode(502);
			return new ResponseEntity<CustomResponse>(customResponse , HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse>  loginUser(@RequestBody User user ,HttpSession session) {
		CustomResponse customResponse = new CustomResponse();
		try {
			user = userService.login(user);
			if(user != null){
				String jwt = GenerateJWT.generate(user.getEmail());
				customResponse.setMessage(jwt);
				customResponse.setStatusCode(200);
				
				session.setAttribute("tokenlogin", jwt);
				return new ResponseEntity<CustomResponse>(customResponse , HttpStatus.OK);
			} else {
				customResponse.setMessage("Your account is not activate");
				customResponse.setStatusCode(502);
				return new ResponseEntity<CustomResponse>(customResponse , HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			customResponse.setMessage("Invalid Details");
			customResponse.setStatusCode(500);
			return new ResponseEntity<CustomResponse>(customResponse , HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public CustomResponse  forgetpassword(@RequestBody User user, HttpServletRequest request , HttpSession session) throws IOException {
		CustomResponse customResponse = new CustomResponse();
		try {
			user = userService.emailValidation(user.getEmail());
			if(user != null) {
				
				String url = request.getRequestURL().toString();
				url = url.substring(0, url.lastIndexOf("/")) + "/" + "resetpassword/" + user.getAuthenticate_user_key();
				sendmail.sendMail("tradefinancebridgelabz@gmail.com", user.getEmail(), "Reset Password", url);
				
				customResponse.setMessage("Please check your mail");
				customResponse.setStatusCode(200);
			}else {
				customResponse.setMessage("User is not found");
				customResponse.setStatusCode(502);
			}
		} catch (Exception e) {
			e.printStackTrace();
			customResponse.setMessage("Exception");
			customResponse.setStatusCode(500);
		}
		return customResponse;
	}
	
	@RequestMapping(value = "/resetpassword/{authenticate_user_key:.+}", method = RequestMethod.GET)
	public CustomResponse resetpassword(@PathVariable("authenticate_user_key") String authenticate_user_key, HttpSession session, HttpServletResponse response ) throws IOException {
		CustomResponse customResponse = new CustomResponse();
		User user = null;
		try {
			user = userService.getUserByAuthenticateKey(authenticate_user_key);
			if(user != null)
			{
				session.setAttribute("Authenticate_user_key", authenticate_user_key);
				customResponse.setMessage("Reset Page");
				customResponse.setStatusCode(200);
				response.sendRedirect("http://localhost:8080/TradeFinance/#!/resetpassword");
			}else {
				customResponse.setMessage("Oops!  This is an invalid password reset link.");
				customResponse.setStatusCode(500);
			}
		}catch (Exception e) {
			e.printStackTrace();
			customResponse.setMessage("Exception");
			customResponse.setStatusCode(500);
		}
		return customResponse;
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.PUT)
	public CustomResponse resetpassword(@RequestBody User user1,HttpServletRequest request, HttpSession session) throws IOException {
		CustomResponse customResponse = new CustomResponse();
		
		//int id = VerifiedJWT.verify(request.getHeader("token"));
		String token = (String) session.getAttribute("Authenticate_user_key");
		User user = null;
		try {
			user = userService.getUserByAuthenticateKey(token);
			if(user != null)
			{
				user.setPassword(encryption.encryptPassword(user1.getPassword()));
				userService.updateUser(user);
				customResponse.setMessage("Reset password seccessfully");
				customResponse.setStatusCode(200);
				
			}else {
				customResponse.setMessage("Invalid");
				customResponse.setStatusCode(500);
			}
		}catch (Exception e) {
			e.printStackTrace();
			customResponse.setMessage("Exception");
			customResponse.setStatusCode(500);
		}
		//session.removeAttribute("Authenticate_user_key");
		return customResponse;
		
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> logout(HttpSession session) 
	{
		CustomResponse customResponse = new CustomResponse();
		if(session!=null){
			
		session.removeAttribute("tokenlogin");
		session.removeAttribute("user");
		session.invalidate();
		customResponse.setMessage("Logout seccessful");
		
		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
		
		} else {
			customResponse.setMessage("Logout Unseccessful");
			return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CONFLICT);
		}
	}
	
	
	
	
}
