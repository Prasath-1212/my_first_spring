package com.my_first_spring.ws.model.request;

import jakarta.validation.constraints.NotNull;

public class UpdateUserDetailsRequestModel {

	@NotNull(message = "FirstName can't be null")
	private String firstName;
	@NotNull
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
