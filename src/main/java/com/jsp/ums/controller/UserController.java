package com.jsp.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responcedto.UserResponce;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponceStructure;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	
	//1st way
//	@PostMapping("/user")
//	public ResponseEntity<User> saveUser(@RequestBody User user) {
//
//		return userService.saveUser(user);
//	}

	@PostMapping("/user")
	public ResponseEntity<ResponceStructure<UserResponce>> saveUser(@RequestBody UserRequest userRequest) {

		return userService.saveUser(userRequest);
	}
	
	
	
	@PutMapping("user/{userId}")
	public ResponseEntity<ResponceStructure<UserResponce>> updateById( @PathVariable int userId,@RequestBody User user){
		
		return  userService.updateById(userId,user);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ResponceStructure<UserResponce>> deleteById(@PathVariable int userId){
		
		
		return userService.deleteById(userId);
	}
//	public List<User> getAllUser(){
//		 List<User> user=userService.getAllUser();
//		
//		return  user;
//	}
}
