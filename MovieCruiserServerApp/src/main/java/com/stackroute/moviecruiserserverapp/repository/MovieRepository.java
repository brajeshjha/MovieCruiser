package com.stackroute.moviecruiserserverapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiserserverapp.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	List<Movie> findByUserId(String userId);

	Movie findByMovieId(int movieId);
}
