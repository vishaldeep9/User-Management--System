package com.jsp.ums.requestdto;

import com.jsp.ums.responcedto.UserResponce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	private String userEmail;
	private String userName;


}
