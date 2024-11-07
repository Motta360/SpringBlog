package com.LucasMotta.SpringBlog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.LucasMotta.SpringBlog.services.UserService;

@Configuration
@EnableWebSecurity
public class Security {
	@Autowired
	UserService userService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return userService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
			
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
		https.formLogin(login -> {
			login.loginPage("/login").permitAll();
			login.defaultSuccessUrl("/");
		})
		
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/","/register").permitAll();
			auth.anyRequest().authenticated();
		});
		
		
		return https.build();
	}

}
