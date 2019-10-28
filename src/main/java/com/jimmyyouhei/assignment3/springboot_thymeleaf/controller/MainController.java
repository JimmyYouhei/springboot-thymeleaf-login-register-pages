package com.jimmyyouhei.assignment3.springboot_thymeleaf.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UserLoggInCount;
import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UsersList;




@Controller
public class MainController {
	
	@Autowired
	UsersList users;
	
	@Autowired
	UserLoggInCount userLoggInCount;
	
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/")
	public String mainPage() {
		return "index";
	}
	
	@GetMapping("/profile")
	public String profilePage(Model model , Principal principal) {
		String username = principal.getName();
		String password = users.getUsers().get(username);
		Integer logInTimes = userLoggInCount.getUserLoggedInTimes().get(username);
		
		System.out.println(String.valueOf(logInTimes));
		System.out.println(userLoggInCount.toString());
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("logInTimes", logInTimes);
		
		return "profile";
	}
	
	@GetMapping("/userCounter")
	public String counterPage(Model model) {
		String usersLoggedin = userLoggInCount.usersLoggedIn();
		Integer totalLoggedIn = userLoggInCount.totalLoggedIn();
		
		model.addAttribute("usersLoggedIn" , usersLoggedin);
		model.addAttribute("totalLoggedIn" , totalLoggedIn);
		
		return "userCounter";
	}
	

}


