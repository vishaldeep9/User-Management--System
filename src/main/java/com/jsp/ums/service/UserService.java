package com.jsp.ums.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responcedto.UserResponce;
import com.jsp.ums.util.ResponceStructure;

public interface UserService {

	//1st way
//	public ResponseEntity<User> saveUser(User user);
	
	public ResponseEntity<ResponceStructure<UserResponce>> saveUser(UserRequest userRequest);

	public ResponseEntity<ResponceStructure<UserResponce>> updateById(int userId, User user);

	public ResponseEntity<ResponceStructure<UserResponce>> deleteById(int userId);



//	public List<User> getAllUser();

}
