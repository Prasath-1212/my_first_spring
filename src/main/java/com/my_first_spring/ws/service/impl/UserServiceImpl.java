package com.my_first_spring.ws.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.my_first_spring.ws.model.request.UserDetailsRequest;
import com.my_first_spring.ws.model.response.UserDetails;
import com.my_first_spring.ws.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my_first_spring.ws.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	Map<String , UserDetails> users;
	Utils utils ;
	


	public UserServiceImpl() {
		super();
	}



	@Autowired
	public UserServiceImpl(Utils utils) {
		super();
		this.utils = utils;
	}




	@Override
	public UserDetails createUser(UserDetailsRequest userDetailsRequest) {
		// TODO Auto-generated method stub
		String id = utils.generateUserId();
		UserDetails returnValue = new UserDetails();
		returnValue.setLastName(userDetailsRequest.getLastName());
		returnValue.setFirstName(userDetailsRequest.getFirstName());
		returnValue.setEmail(userDetailsRequest.getEmail());
		returnValue.setPhnNo(userDetailsRequest.getPhnNo());
		returnValue.setId(id);
		
		if(users==null) users = new HashMap<>();
		users.put(id, returnValue);
		return returnValue;
	}

}
