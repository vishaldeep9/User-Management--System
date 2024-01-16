package com.jsp.ums.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.ums.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	// here we were getting two error and we just want to show that error that is
	// why we are doing this,

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
//		
//		
//	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> allErrors = ex.getAllErrors();
		
		Map<String, String> errors=new HashMap<String,String>();
        allErrors.forEach(error ->{
        	FieldError fieldError=(FieldError) error;
        	errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        
       return structure(HttpStatus.BAD_REQUEST,"Failed to save Data",errors);
        
	}

	private ResponseEntity<Object> structure(HttpStatus status, String message, Object rootCause) {

		// When they are limited then we can directly use map.of()
		return new ResponseEntity<Object>(Map.of("status", status.value(), "message", message, "rootCause", rootCause),
				status);
	}

	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException ex) {

		return structure(HttpStatus.NOT_FOUND, ex.getMessage(), "user not found by given id");
	}

	public ResponseEntity<Object> handleRuntime(RuntimeException ex) {
		return structure(HttpStatus.BAD_REQUEST, ex.getMessage(), "");
	}
}
