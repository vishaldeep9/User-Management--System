package com.jsp.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.ums.exception.UserNotFoundByIdException;
import com.jsp.ums.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	//Return type of map will be return type of whole object
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	return	userRepo.findByUserName(username).map(user -> new CustomUserDetails(user))
				.orElseThrow(()-> new UserNotFoundByIdException("failed to Authenticate the user"));

	}
}
