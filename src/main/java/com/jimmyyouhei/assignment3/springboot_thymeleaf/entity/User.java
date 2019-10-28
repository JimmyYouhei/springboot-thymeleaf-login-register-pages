package com.jimmyyouhei.assignment3.springboot_thymeleaf.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	@NotBlank(message = "must not be blank")
	@Size(min = 7 , message = "must be >6 char")
	@Pattern(regexp = "[^$#@%^&*]+" , message = "cannot contain any char: '^$#@%^&*' ")
	private String username;
	
	
	@NotBlank(message = "must not be blank")
	@Size(min = 9 , message = "must be > 8 char")
	@Pattern(regexp = ".*[A-Z]+.*" , message = "must contain at least 1 Capitalize char")
	@Pattern(regexp =".*[0-9]+.*" , message = "must contain at least 1 digit " )
	@Pattern(regexp = ".*[^A-Z0-9a-z\\s]+.*" , message = "must contain at least 1 special Char")
	private String password;
	
	public User() {
		
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
