package com.jimmyyouhei.assignment3.springboot_thymeleaf.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UserLoggInCount;


@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	UserLoggInCount userLoggInCount;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		

		String username = authentication.getName();
		
	
		if(userLoggInCount.getUsers() == null) {
			userLoggInCount.setUsers(new ArrayList<String>());
			userLoggInCount.addUser(username);
			
			userLoggInCount.setUserLoggedInTimes(new LinkedHashMap<String, Integer>());
			userLoggInCount.addCount(username);
			request.setAttribute("firstLogin", true);
		} else {
			
			if(!userLoggInCount.getUsers().contains(username)) {
				
				userLoggInCount.addUser(username);
				userLoggInCount.addCount(username);
			} else {
				userLoggInCount.addCountOldUser(username);
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
		
	}

	
	
}
