package com.jsp.ums.serviceimple;

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
	
//	@Autowired
//	private ResponceStructure<User> structure;

	// 1st Way
//	@Override
//	public ResponseEntity<User> saveUser(User user) {
//
//		 User user2 = userRepo.save(user);
//		return new ResponseEntity<User>(user2,HttpStatus.CREATED);
//	}

	@Override
	public ResponseEntity<ResponceStructure<UserResponce>> saveUser(UserRequest userRequest) {
		User user2=mapToUserRequest(userRequest);
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
	
	@Override
	public ResponseEntity<ResponceStructure<UserResponce>> deleteById(int userId) {
	User user3 = userRepo.findById(userId).get();
//			.map(u ->{
//				userRepo.delete(user3);
//			}).orElseThrow(()-> new RuntimeException());
	
	if (user3 !=null)
		userRepo.delete(user3);
		structure.setStatus(HttpStatus.GONE.value());
		structure.setMessage("Deleted By Id");
//		structure.setData(mapToUserResponce(user3));
		return new ResponseEntity<ResponceStructure<UserResponce>>(structure,HttpStatus.GONE);
	}
	
	private User mapToUserRequest(UserRequest request) {
		return User.builder()
				.userEmail(request.getUserEmail())
				.userName(request.getUserName())
				.build();
	}
	
	private UserResponce mapToUserResponce(User user) {
		return UserResponce.builder()
				.userEmail(user.getUserEmail())
				.build();
	}






//	public List<User> getAllUser(){
//		return 
//	}

}
