package com.example.moviedb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHanlder {

	@ExceptionHandler
	public final ResponseEntity<Object> handleMovieAlreadyExists(MovieAlreadyExistsException ex, WebRequest request) {
		MovieAlreadyExistsExceptionResponse exceptionResponse = new MovieAlreadyExistsExceptionResponse(
				ex.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleMovieNotFoundIdException(MovieNotFoundException ex, WebRequest request) {
		MovieNotFoundExceptionResponse exceptionResponse = new MovieNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleCustomIlligalArgumentException(CustomIllegalArgumentException e) {

		CustomIllegalArgumentExceptionResponse exceptionResponse = new CustomIllegalArgumentExceptionResponse("failed",
				e.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}
}
