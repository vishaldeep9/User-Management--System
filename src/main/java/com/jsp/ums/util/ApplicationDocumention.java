package com.jsp.ums.util;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumention {

	Contact contact() {

		return new Contact().email("09vishaldeep@gmail.com")
				.url("xyz.com");

	}
	Info info() {
		return new Info()
				.title("User Management System Api")
				.version("1.00")
				.description("user management system is a RESTful API built using " + "spring boot and MYSQL database")
				.contact(contact());
	}

	OpenAPI openAPI() {

		return new OpenAPI().info(info());
	}


}
