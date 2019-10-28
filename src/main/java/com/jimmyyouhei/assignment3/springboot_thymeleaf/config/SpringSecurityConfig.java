package com.jimmyyouhei.assignment3.springboot_thymeleaf.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jimmyyouhei.assignment3.springboot_thymeleaf.bean.UsersList;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Autowired
	AuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	UsersList users;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(inMemoryUserDetailsManager());
		
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		Collection<UserDetails> usersList = new ArrayList<>();
		
		if (users.isEmpty()) {
			System.out.println("empty");
			String path = users.getContext().getRealPath(users.getRelativePath());
			System.out.println(path);
		} else {
			
			for (String username : users.getUsers().keySet()) {
			usersList.add(User.withDefaultPasswordEncoder().username(username).password(users.getUsers().get(username)).roles("USER").build());
			}
			
		}
		
		return new InMemoryUserDetailsManager(usersList);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/signUp").permitAll()
		.antMatchers("/signUpProccess").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/loginPage")
		.loginProcessingUrl("/authenticateUser")
		.successHandler(customAuthenticationSuccessHandler)
		.failureHandler(customAuthenticationFailureHandler)
		.permitAll()
		.and()
		.logout().permitAll();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/statics/**");
	}
	

}




