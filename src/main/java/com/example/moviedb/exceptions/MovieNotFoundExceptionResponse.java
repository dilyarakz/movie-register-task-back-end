package com.example.moviedb.exceptions;

public class MovieNotFoundExceptionResponse {

	private String MovieNotFound;

	public String getMovieNotFound() {
		return MovieNotFound;
	}

	public void setMovieNotFound(String movieNotFound) {
		MovieNotFound = movieNotFound;
	}

	public MovieNotFoundExceptionResponse(String movieNotFound) {
		MovieNotFound = movieNotFound;
	}

}
