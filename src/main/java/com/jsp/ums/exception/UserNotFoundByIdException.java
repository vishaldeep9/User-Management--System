package com.jsp.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {

private String message;
private String status;
private String rootCause;
public UserNotFoundByIdException(String message) {
	super();
	this.message = message;
}
}
