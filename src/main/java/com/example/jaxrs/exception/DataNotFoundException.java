package com.example.jaxrs.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5500869766658759316L;

	public DataNotFoundException(String message) {
		super(message);
	}
	
}
