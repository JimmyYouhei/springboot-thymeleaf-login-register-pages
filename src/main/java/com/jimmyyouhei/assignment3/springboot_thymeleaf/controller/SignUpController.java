package com.jimmyyouhei.assignment3.springboot_thymeleaf.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UsersList;
import com.jimmyyouhei.assignment3.springboot_thymeleaf.entity.User;


@Controller
public class SignUpController {

	@Autowired
	UsersList users;
	
	@Autowired
	InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	
	@GetMapping("/signUp")
	public String signUpPage(Model model) {
		User newUser = new User();
		
		model.addAttribute("user" , newUser);
		
		return "sign-up";
		
	}
	
	
	@PostMapping("/signUpProccess")
	public String sigUpProcess(@Valid @ModelAttribute User newUser , BindingResult bindingResult , Model model ) throws Exception {
		
		
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		
		if(bindingResult.hasErrors()) {
			return "sign-up";
		} else {
			
			if(users.getUsers() == null) {
				registerUser(username, password);
				model.addAttribute("firstSignUp", true);
				return "login";
			}
			
			if(users.getUsers().containsKey(username)) {
				System.out.println("username exist");
				model.addAttribute("usernameExist", true);
				
				return "sign-up";
				
			} else {
				registerUser(username, password);
				return "index";
			}
			
		}
		
		
	}


	private void registerUser(String username, String password) throws IOException {
		users.addAndWriteUsersToFile(username, password);
		UserBuilder builder = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();
		inMemoryUserDetailsManager.createUser(builder.username(username).password(password).roles("USER").build());
	}
	
}



