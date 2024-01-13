package com.jsp.ums.serviceimple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.ums.entity.User;
import com.jsp.ums.repo.UserRepo;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responcedto.UserResponce;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponceStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ResponceStructure<UserResponce> structure;

	// get All Data
	@Autowired
	private ResponceStructure<List<UserResponce>> listStructure;

	// 1st Way
//	@Override
//	public ResponseEntity<User> saveUser(User user) {
//
//		 User user2 = userRepo.save(user);
//		return new ResponseEntity<User>(user2,HttpStatus.CREATED);
//	}

	@Override
	public ResponseEntity<ResponceStructure<UserResponce>> saveUser(UserRequest userRequest) {
		User user2 = mapToUserRequest(userRequest);
		User user = userRepo.save(user2);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("user data has been created");
		structure.setData(mapToUserResponce(user));
		return new ResponseEntity<ResponceStructure<UserResponce>>(structure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponceStructure<UserResponce>> updateById(int userId, User user) {
		// 1st Way
//		// ValidaTing that user Of that id is present or not,if find then directly it will return User object
//		User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException());
//
//		// Reassigning by that id, if we comment off than it will create new object
//		user.setUserId(userId);
//		User user2 = userRepo.save(user);

		// 2nd Ways[if condition follow then it will execute map one otherwise gives
		// exception
		User user2 = userRepo.findById(userId).map(u -> {
			user.setUserId(userId);
			return userRepo.save(user);

		}).orElseThrow(() -> new RuntimeException());

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User has been   Updated By Id");
		structure.setData(mapToUserResponce(user2));

		return new ResponseEntity<ResponceStructure<UserResponce>>(structure, HttpStatus.OK);
	}

//delete doesnot have return type
	@Override
	public ResponseEntity<ResponceStructure<UserResponce>> deleteById(int userId) {

		structure.setStatus(HttpStatus.GONE.value());
		structure.setMessage("Deleted By Id");
		structure.setData(mapToUserResponce(userRepo.findById(userId).map(u -> {
			userRepo.delete(u);
			return u;
		}).orElseThrow(() -> new RuntimeException())));
		return new ResponseEntity<ResponceStructure<UserResponce>>(structure, HttpStatus.GONE);
	}

	@Override
	public ResponseEntity<ResponceStructure<List<UserResponce>>> getAllUser(User user) {
		List<User> findAll = userRepo.findAll();

		//to get All Data ,otherwise only one data will come
		List<UserResponce> responces = new ArrayList<>();
		for (User user1 : findAll) {
			UserResponce userResponce = mapToUserResponce(user1);
			responces.add(userResponce);
			listStructure.setStatus(HttpStatus.OK.value());
			listStructure.setMessage("All data has been fetched");
			listStructure.setData(responces);
		}
		return new ResponseEntity<ResponceStructure<List<UserResponce>>>(listStructure, HttpStatus.OK);
	}

	private User mapToUserRequest(UserRequest request) {
		return User.builder().userEmail(request.getUserEmail()).userName(request.getUserName()).build();
	}

	private UserResponce mapToUserResponce(User user) {
		return UserResponce.builder().userEmail(user.getUserEmail()).build();
	}

}
