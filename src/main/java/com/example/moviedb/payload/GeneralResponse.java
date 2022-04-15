package com.example.moviedb.payload;

public class GeneralResponse {

	private String message;

	public GeneralResponse(String message) {
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
