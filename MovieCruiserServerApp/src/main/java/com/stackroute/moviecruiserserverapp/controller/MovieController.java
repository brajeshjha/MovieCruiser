package com.stackroute.moviecruiserserverapp.controller;


import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapp.domain.Movie;
import com.stackroute.moviecruiserserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapp.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapp.service.MovieService;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin
public class MovieController {
	

	private MovieService movieService;

	public MovieController(final MovieService movieService) {
		// TODO Auto-generated constructor stub
		this.movieService=movieService;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllMovies(final ServletRequest req, final ServletResponse res)
	{
		ResponseEntity<?> responseEntity;
		final HttpServletRequest request=(HttpServletRequest)req;
		final String authHeader=request.getHeader("Authorization");
		final String token=authHeader.split(" ")[1];
		final String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	
		
		final List<Movie> movieList=this.movieService.getMyMovies(userId);
		
		responseEntity=new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
		
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") final int id)
	{
		ResponseEntity<?> responseEntity;
		try {
			final Movie movie =this.movieService.getMovieById(id);
			responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.OK);
			
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}		
		return responseEntity;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> saveMovie(@RequestBody final Movie movie, final ServletRequest req, final ServletResponse res)
	{
		System.out.println(movie.getMovieId());
		ResponseEntity<?> responseEntity;
		try {
			final HttpServletRequest request=(HttpServletRequest)req;
			final String authHeader=request.getHeader("authorization");
			final String token=authHeader.substring(7);
			final String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			
			movie.setUserId(userId);
			this.movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}		
		return responseEntity;
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateMovie(@RequestBody final Movie movie)
	{
		ResponseEntity<?> responseEntity;
		try {
			final Movie updatedmovie =this.movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(updatedmovie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}		
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id")final int id)
	{
		ResponseEntity<?> responseEntity;
		try {
			this.movieService.deleteMovieById(id);
			responseEntity=new ResponseEntity<String>("{ \"message\": \"" + "Movie deleted successfully" + "\"}", HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}		
		
		return responseEntity;
	}
	

}
