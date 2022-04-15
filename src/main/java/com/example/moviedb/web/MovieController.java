package com.example.moviedb.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviedb.domain.Movie;
import com.example.moviedb.payload.GeneralResponse;
import com.example.moviedb.services.MapValidationErrorService;
import com.example.moviedb.services.MovieService;

@RestController
@RequestMapping("/api/1.0/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * Create a new Movie
	 * 
	 * @param movie
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> createMovie(@Valid @RequestBody Movie movie, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		Movie newMovie = movieService.createMovie(movie);

		return new ResponseEntity<GeneralResponse>(
				new GeneralResponse("Movie with ID: '" + newMovie.getId() + "' created."), HttpStatus.CREATED);
	}

	/**
	 * Get a movie using an id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readMovie(@PathVariable Long id) {

		Movie movie = movieService.getMovie(id);

		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

	/**
	 * Update a movie
	 * 
	 * @param id
	 * @param updatedMovie
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie updatedMovie,
			BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		Movie movie = movieService.getMovie(id);
		if (movie != null) {
			movieService.updateMovie(id, updatedMovie);
		}

		return new ResponseEntity<GeneralResponse>(new GeneralResponse("Movie with ID: '" + id + "' was updated."),
				HttpStatus.OK);

	}

	/**
	 * Delete a movie
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
		movieService.getMovie(id);

		movieService.deleteMovie(id);

		return new ResponseEntity<GeneralResponse>(new GeneralResponse("Movie with ID: '" + id + "' deleted."),
				HttpStatus.OK);

	}

	/**
	 * Search for movies based on title
	 * 
	 * @param title
	 * @return
	 */
	@GetMapping("/search/{title}")
	public ResponseEntity<?> searchMovie(@PathVariable String title) {
		List<Movie> movies = movieService.searchMovies(title);

		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}

	/**
	 * Get all movies
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public Iterable<Movie> getAllMovies() {

		return movieService.getAll();
	}

	/**
	 * Add like/dislike to the movie
	 * 
	 * @param id
	 * @param like
	 * @return
	 */
	@PutMapping("/{id}/likes/{like}")
	public ResponseEntity<?> addlike(@PathVariable Long id, @PathVariable Boolean like) {

		return movieService.addLike(id, like) == 1
				? new ResponseEntity<GeneralResponse>(
						new GeneralResponse("Likes/Dislikes for movie with ID : '" + id + "' successfully updated."),
						HttpStatus.OK)
				: new ResponseEntity<GeneralResponse>(
						new GeneralResponse("Likes/Dislikes for movie with ID : '" + id + "' failed to update."),
						HttpStatus.OK);
	}
}
