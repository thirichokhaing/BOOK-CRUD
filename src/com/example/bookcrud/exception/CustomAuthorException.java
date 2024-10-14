package com.example.bookcrud.exception;

public class CustomAuthorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomAuthorException(String message) {
		super(message);
	}
	
	public CustomAuthorException(String message, Throwable cause) {
		super(message,cause);
	}
}
