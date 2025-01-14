package com.my_first_spring.ws.controller;

import java.util.HashMap;
import java.util.Map;

import com.my_first_spring.ws.model.request.UpdateUserDetailsRequestModel;
import com.my_first_spring.ws.model.request.UserDetailsRequest;
import com.my_first_spring.ws.model.response.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my_first_spring.ws.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService ;
	
	Map<String , UserDetails> users = new HashMap<>();

	@GetMapping()
	public String getUsers( @RequestParam(value="page") int page , @RequestParam(value="limit") int limit ,
			@RequestParam(value="sort" , required = false)String sort   )
	{
		return "Getting the users  of page no :" + page + " and limit : " + limit + " sort : "+ sort ;
	}
	
	@GetMapping(path = "/{userid}" , produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}  ) 
	public ResponseEntity<UserDetails> getUser(@PathVariable String userid)	{
		
		if(users.containsKey(userid))
		{
			return new ResponseEntity<UserDetails>(users.get(userid), HttpStatus.OK);
		}
		else
		{	
			return new ResponseEntity<UserDetails>(HttpStatus.NO_CONTENT);
		}
	
	}
	
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDetails> addUser(@Valid  @RequestBody UserDetailsRequest userDetailsRequest)
	{
		UserDetails returnValue = userService.createUser(userDetailsRequest);
		users.put(returnValue.getId(), returnValue);
		return new ResponseEntity<UserDetails>(returnValue , HttpStatus.OK);
		
	}
	
	
	@PutMapping(path="/{userid}" , consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )
	public UserDetails updateUser(@PathVariable String userid , @RequestBody UpdateUserDetailsRequestModel updateUserDetails)
	{
		
		
		UserDetails user = users.get(userid);
		user.setFirstName(updateUserDetails.getFirstName());
		user.setLastName(updateUserDetails.getLastName());
		users.put(userid, user);
		
		return user ;
	}
	
	@DeleteMapping(path= "/{userid}")
	public ResponseEntity<Void> DeleteUser(@PathVariable String userid)
	{
		users.remove(userid);
		return ResponseEntity.noContent().build();
	}

}
