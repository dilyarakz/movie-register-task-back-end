package com.example.moviedb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviedb.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(value = "SELECT * FROM movie m WHERE m.title LIKE %?1%", nativeQuery = true)
	List<Movie> findByTitle(String title);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE movie m SET m.likes = m.likes + 1 \n" + "WHERE m.id = ?1", nativeQuery = true)
	Integer setLikesById(Long id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE movie m SET m.dislikes = m.dislikes + 1 \n" + "WHERE m.id = ?1", nativeQuery = true)
	Integer setDislikesById(Long id);

	@Query(value = "SELECT likes FROM movie m WHERE m.id = ?1", nativeQuery = true)
	Integer findLikesById(Long id);

	@Query(value = "SELECT dis_likes FROM movie m WHERE m.id = ?1", nativeQuery = true)
	Integer findDisLikesById(Long id);
}
