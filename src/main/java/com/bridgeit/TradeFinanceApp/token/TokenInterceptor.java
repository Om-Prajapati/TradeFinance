package com.bridgeit.TradeFinanceApp.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Om Prajapati
 * 
 * this class is use to check and varify the JWT token  
 * and set the userId to the request  using setAttribute
 *
 */
public class TokenInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		int userId = VerifiedJWT.verify(request.getHeader("token"));
		if (userId == 0) {
			response.setStatus(511);
			return false;
		}
		request.setAttribute("userId", userId);
		return true;
	}

}
