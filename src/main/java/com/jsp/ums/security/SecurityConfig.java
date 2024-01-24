package com.jsp.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	// BCrytptPasswordEncoder is the implement class of PasswordEncoder
	// we are encrypting the password here,BCryptPasswordEncoder is used for
	// password hashing in Java applications. It provides a secure way to encrypt
	// passwords by using a one-way hashing algorithm
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	// Appication Manager
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// first disabled already existing/by default security
		// this line is->for all request will be be Authenticate
		// we are setting default form , Authentication format

		return httpSecurity.csrf(csrf -> csrf.disable())
				// .authorizeHttpRequests(auth ->
				// auth.requestMatchers("/**").permitAll().anyRequest().authenticated())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).build();
	}

	// Application Provider
	@Bean
	AuthenticationProvider authenticationProvider() {

		// It is responsible for authenticating a user against a user details service
		// using userName and password
		// `DaoAuthenticationProvider` will retrieve the user's details from the
		// `UserDetailsService` and compare the provided password with the hashed
		// password stored in the user's details object.If the passwords match, the user
		// is considered authenticated
		// and a `UsernamePasswordAuthenticationToken` is returned.
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		// It can be configured with a `PasswordEncoder` implementation to encode the
		// password before comparing it to the stored password.
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
