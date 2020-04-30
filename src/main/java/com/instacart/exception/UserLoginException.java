package com.instacart.exception;

public class UserLoginException extends Exception{
	
	public UserLoginException(String message)
	{
		super("UserLoginException-"+message);
	}
	
	public UserLoginException(String message, Throwable cause)
	{
		super("UserLoginException-"+message,cause);
	}

}
