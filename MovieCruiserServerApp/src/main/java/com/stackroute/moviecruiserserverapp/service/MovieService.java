package com.stackroute.moviecruiserserverapp.service;

import java.util.List;

import com.stackroute.moviecruiserserverapp.domain.Movie;
import com.stackroute.moviecruiserserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapp.exception.MovieNotFoundException;

public interface MovieService {
	
	public Movie getMovieById(final int id) throws MovieNotFoundException;
	
	public boolean saveMovie(final Movie movie)throws MovieAlreadyExistsException;
	
	public Movie updateMovie(final Movie movie)throws MovieNotFoundException;
	
	public List<Movie> getMyMovies(String userId);
	
	public boolean deleteMovieById(final int id)throws MovieNotFoundException;
	
}
