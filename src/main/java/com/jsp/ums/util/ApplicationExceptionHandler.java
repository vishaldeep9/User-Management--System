package com.jsp.ums.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ums.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	
	private ResponseEntity<Object> structure(HttpStatus status,String message,Object rootCause){
		
		//When they are limited then we can directly use map.of()
		return new ResponseEntity<Object> ( Map.of(
				"status",status.value(),
				"message",message,
				"rootCause",rootCause
				),status);
	}
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException ex){
		
		return structure(HttpStatus.NOT_FOUND, ex.getMessage(),"user not found by given id");
	}
	
	public ResponseEntity<Object> handleRuntime(RuntimeException ex){
		return structure(HttpStatus.BAD_REQUEST, ex.getMessage(), "");
	}
}
