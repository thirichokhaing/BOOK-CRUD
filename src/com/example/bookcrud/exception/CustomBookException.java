package com.example.bookcrud.exception;

public class CustomBookException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomBookException(String message) {
		super(message);
	}
	
	public CustomBookException(String message, Throwable cause) {
		super(message,cause);
	}

}
