package com.jimmyyouhei.assignment3.springboot_thymeleaf.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UsersList;


@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	UsersList users;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		if (users.isEmpty()) {
			request.setAttribute("noUser", true); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginPage");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("loginError" , true); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginPage");
			dispatcher.forward(request, response);
		}

	}
	


}
