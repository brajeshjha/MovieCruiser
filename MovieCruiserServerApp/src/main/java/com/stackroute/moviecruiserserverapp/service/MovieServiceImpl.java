package com.stackroute.moviecruiserserverapp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapp.domain.Movie;
import com.stackroute.moviecruiserserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapp.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapp.repository.MovieRepository;
import com.stackroute.moviecruiserserverapp.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	private final transient MovieRepository movieRepository; 

	@Autowired
	public MovieServiceImpl(final MovieRepository movieRepository) {
		super();
		this.movieRepository=movieRepository;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Movie getMovieById(final int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> movie=this.movieRepository.findById(id);
		
		if(movie.isPresent())
		{
			return movie.get();
		}
		else
		{
			throw new MovieNotFoundException("Movie not found");
		}
	}

	@Override
	public boolean saveMovie(final Movie movie) throws MovieAlreadyExistsException {
		// TODO Auto-generated method stub
		
		Optional<Movie> movieObject=this.movieRepository.findById(movie.getId());
		
		if(movieObject.isPresent())
		{
			throw new MovieAlreadyExistsException("Movie Could not be saved, Movie already exist");
		}		
		
		this.movieRepository.save(movie);
		return true;
			
	}

	@Override
	public Movie updateMovie(final Movie updateMovie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie=this.movieRepository.findById(updateMovie.getId()).orElse(null);
		if(movie==null)
		{
			throw new MovieNotFoundException("Could not update movie.Movie not found");
		}
		movie.setComments(updateMovie.getComments());
		return this.movieRepository.save(movie);
		
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		// TODO Auto-generated method stub
		return this.movieRepository.findByUserId(userId);
	}

	@Override
	public boolean deleteMovieById(final int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie=this.movieRepository.findById(id).orElse(null);
		if(movie==null)
		{
			throw new MovieNotFoundException("Could not delete.Movie not found");
		}
		this.movieRepository.delete(movie);
		return true;
	}


}

