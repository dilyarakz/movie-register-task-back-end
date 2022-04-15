package com.example.moviedb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/***
 * Custom Exception
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomIllegalArgumentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomIllegalArgumentException(String message) {
		super(message);
	}
}