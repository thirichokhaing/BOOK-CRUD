package com.example.bookcrud.exception;

public class CustomOrderException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomOrderException(String message) {
		super(message);
	}
	
	public CustomOrderException(String message, Throwable cause) {
		super(message,cause);
	}

}
