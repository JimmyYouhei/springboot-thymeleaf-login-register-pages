package com.jimmyyouhei.assignment3.springboot_thymeleaf.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
public class UserLoggInCount {
	
	private List<String> users;
	private Map<String , Integer> userLoggedInTimes;
	
	public UserLoggInCount() {
		System.out.println("init logincount");
	}
	
	
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public Map<String, Integer> getUserLoggedInTimes() {
		return userLoggedInTimes;
	}
	public void setUserLoggedInTimes(Map<String, Integer> userLoggedInTimes) {
		this.userLoggedInTimes = userLoggedInTimes;
	}

	public void addUser(String username) {
		
		users.add(username);
	}
	
	public void addCount(String username) {
		userLoggedInTimes.put(username, 1);
	}


	public void addCountOldUser(String username) {
		userLoggedInTimes.put(username, userLoggedInTimes.get(username) + 1 );
		
	}
	
	public String usersLoggedIn() {
		StringBuilder sb = new StringBuilder();
		
		for(String user : users) {
			sb.append(user);
			sb.append(" , ");
		}
		
		sb.setLength(sb.length()-3);
		
		return sb.toString();
		
	}
	
	public Integer totalLoggedIn() {
		Integer result = 0;
		
		for(String user : userLoggedInTimes.keySet()) {
			result += userLoggedInTimes.get(user);
		}
		
		return result;
		
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(String user : users) {
			sb.append("user: " +user);
			sb.append(" and ");
		}
		
		
		for(String user: userLoggedInTimes.keySet()) {
			sb.append("map: " + user);
			sb.append(" have ");
			sb.append(userLoggedInTimes.get(user));
			sb.append(" and ");
		}
		
		return sb.toString();
		
	}
	
	
	
	

}
