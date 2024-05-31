package com.my_first_spring.ws.service;

import com.my_first_spring.ws.model.request.UserDetailsRequest;
import com.my_first_spring.ws.model.response.UserDetails;

public interface UserService {

	public UserDetails createUser(UserDetailsRequest userDetailsRequest);
}
