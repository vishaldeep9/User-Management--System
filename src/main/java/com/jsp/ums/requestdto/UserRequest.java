package com.jsp.ums.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	@NotNull(message="username email cannot be null")
	private String userEmail;
	
	@NotBlank(message="userName cannot be blank")
	private String userName;


}
