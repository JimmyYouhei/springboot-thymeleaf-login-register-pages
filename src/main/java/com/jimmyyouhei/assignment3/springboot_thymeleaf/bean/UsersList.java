package com.jimmyyouhei.assignment3.springboot_thymeleaf.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class UsersList {

	private ServletContext context;
	
	private String relativePath;
	
	private Map<String, String> users;
	
	private Boolean isEmpty = true; 

	
	public UsersList() {
		
		
	}


	public UsersList(String relativePath , ServletContext context) throws IOException {
		this.relativePath = relativePath;
		this.context = context;
		
		readUsersFromFile();

	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}



	public String getRelativePath() {
		return relativePath;
	}



	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}



	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}
	
	
	
	public boolean isEmpty() {
		return isEmpty;
	}


	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}


	private void readUsersFromFile() throws IOException {
		String path = context.getRealPath(relativePath);
		File usersFile = new File(path);
		
		if(!usersFile.exists()||usersFile.length()==0) {
			this.isEmpty = true;
			usersFile.createNewFile();
		} else {
			this.isEmpty = false;
			readUsers(usersFile);
		}
		
	}
	
	private void readUsers(File usersFile) throws FileNotFoundException {
		this.users = new LinkedHashMap<>() ;
		
		String username = null;
		String password = null;
		
		Scanner sc = new Scanner(usersFile);
		while(sc.hasNextLine()) {
			
			if(username == null) {
				username = sc.nextLine().substring(10);
			}else {
				password = sc.nextLine().substring(10);
				
				users.put(username, password);
				
				username = null;
				password = null;
				
			}
			
		}
		
		sc.close();
	}
	
	public void addAndWriteUsersToFile(String username , String password) throws IOException {
		if(users == null) {
			users = new LinkedHashMap<>();
		}
		
		this.users.put(username, password);
		
		String path = context.getRealPath(relativePath);
		File usersFile = new File(path);
		
		FileWriter writer = new FileWriter(usersFile);
		PrintWriter printWriter = new PrintWriter(writer);
		
		for(String key: users.keySet()) {
			printWriter.println("username: " + key);
			printWriter.println("password: " + users.get(key));
		}
		
		printWriter.close();
		
	}
	
}


