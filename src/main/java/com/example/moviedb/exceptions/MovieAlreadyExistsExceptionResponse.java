package com.example.moviedb.exceptions;

public class MovieAlreadyExistsExceptionResponse {

	private String message;

	public MovieAlreadyExistsExceptionResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
