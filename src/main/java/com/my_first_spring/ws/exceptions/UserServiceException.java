package com.my_first_spring.ws.exceptions;

public class UserServiceException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6996138592257742155L;

	public UserServiceException(String message)
	{
		super(message);
	}
	
	
}
