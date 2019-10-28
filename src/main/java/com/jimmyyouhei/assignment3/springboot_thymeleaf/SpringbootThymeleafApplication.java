package com.jimmyyouhei.assignment3.springboot_thymeleaf;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UsersList;


@SpringBootApplication
public class SpringbootThymeleafApplication {
	
	@Autowired
	ServletContext context;

	private final String USERSFILE = "/users.txt";


	@Bean()
	UsersList users() throws IOException {
		UsersList users = new UsersList(USERSFILE , context);
		
		return users;
	}
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringbootThymeleafApplication.class, args);
	}

	

	
}
