package com.main.Exception;

public class JWTAuthenticationException extends RuntimeException{
	
	public JWTAuthenticationException(String message) {
		super();
	}
	
	public JWTAuthenticationException(String message, Throwable cause) {
		super(message,cause);
	}

}
