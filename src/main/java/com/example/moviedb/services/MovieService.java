package com.example.moviedb.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.example.moviedb.domain.Movie;
import com.example.moviedb.exceptions.MovieNotFoundException;
import com.example.moviedb.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	/**
	 * Create a movie
	 * 
	 * @param movie
	 * @return
	 */
	public Movie createMovie(Movie movie) {

		return movieRepository.save(movie);

	}

	/**
	 * Get a movie based on id
	 * 
	 * @param id
	 * @return
	 */
	public Movie getMovie(Long id) {

		try {
			Movie movie = movieRepository.getById(id);

			movie.getTitle();
			return movie;

		} catch (EntityNotFoundException e) {
			throw new MovieNotFoundException("Movie with id '" + id + "' not found.");
		}

	}

	/**
	 * Update a movie
	 * 
	 * @param id
	 * @param updatedMovie
	 * @return
	 */
	public Movie updateMovie(Long id, Movie updatedMovie) {

		Movie movie = movieRepository.getById(id);

		if (movie == null) {
			throw new MovieNotFoundException("Movie with id '" + updatedMovie.getId() + "' not found.");
		}
		updatedMovie.setId(id);
		return movieRepository.save(updatedMovie);

	}

	/**
	 * Search for movies base on title
	 * 
	 * @param title
	 * @return
	 */
	@CachePut(value = "title")
	public List<Movie> searchMovies(String title) {

		return movieRepository.findByTitle(title);

	}

	/**
	 * Get all movies
	 * 
	 * @return all movies
	 */
	public List<Movie> getAll() {

		return movieRepository.findAll();
	}

	/**
	 * Add like/dislike to a movie
	 * 
	 * @param id
	 * @param like
	 */
	public int addLike(Long id, Boolean like) {

		getMovie(id);

		return like ? movieRepository.setLikesById(id) : movieRepository.setDislikesById(id);

	}

	/**
	 * Delete a movie
	 * 
	 * @param id
	 */
	public void deleteMovie(long id) {

		movieRepository.delete(getMovie(id));
	}

}
