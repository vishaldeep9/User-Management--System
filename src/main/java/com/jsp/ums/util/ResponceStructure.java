package com.jsp.ums.util;

import org.springframework.stereotype.Component;

import com.jsp.ums.requestdto.UserRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ResponceStructure<T> {

	
	private int status;
	private String message;
	private T data;
}
