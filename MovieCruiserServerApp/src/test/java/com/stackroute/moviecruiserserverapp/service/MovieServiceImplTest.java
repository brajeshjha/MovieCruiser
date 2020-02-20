package com.stackroute.moviecruiserserverapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserserverapp.domain.Movie;
import com.stackroute.moviecruiserserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapp.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapp.repository.MovieRepository;
import com.stackroute.moviecruiserserverapp.service.MovieServiceImpl;

public class MovieServiceImplTest {

	public MovieServiceImplTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Mock
	private transient MovieRepository movieRepository;
	
	@InjectMocks
	private transient MovieServiceImpl movieServiceImpl;
	
	private transient Movie movie;
	private transient Optional<Movie> optionalMovie;	
	
	@Before
	public void setUpMocks()
	{
		MockitoAnnotations.initMocks(this);
		movie=new Movie(1,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh");
		optionalMovie=Optional.of(movie);
	}
	
	@Test
	public void testMockCreation()
	{
		assertNotNull("jpaRepository creation fails:use @InjectMocks over movieServiceImpl",movie);
	}
	
	@Test
	public void testSaveMovieSuccess()throws MovieAlreadyExistsException
	{
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag=movieServiceImpl.saveMovie(movie);
		assertTrue("Movie creation failed",flag);
		verify(movieRepository,times(1)).save(movie);
		verify(movieRepository,times(1)).findById(movie.getId());
		
	}
	
	@Test(expected=MovieAlreadyExistsException.class)
	public void testSaveMovieFailure()throws MovieAlreadyExistsException
	{
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		when(movieRepository.save(movie)).thenReturn(movie);	
		final boolean flag=movieServiceImpl.saveMovie(movie);
		assertFalse("Saving movie failed",flag);
		verify(movieRepository,times(1)).findById(movie.getId());
	}
	
	@Test
	public void testUpdateMovie()throws MovieNotFoundException
	{
		when(movieRepository.save(movie)).thenReturn(movie);
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		movie.setComments("Beautiful");
		final Movie updatedMovie=movieServiceImpl.updateMovie(movie);
		assertEquals("Updating Movie failed","Beautiful",updatedMovie.getComments());
		verify(movieRepository,times(1)).save(movie);
		verify(movieRepository,times(1)).findById(movie.getId());
		
	}
	
	@Test
	public void testDeleteMovie()throws MovieNotFoundException
	{
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		doNothing().when(movieRepository).delete(movie);
		final boolean flag=movieServiceImpl.deleteMovieById(movie.getId());
		assertTrue("Deleting Movie failed",flag);
		verify(movieRepository,times(1)).delete(movie);
		verify(movieRepository,times(1)).findById(movie.getId());	
	}
	
	@Test
	public void testGetMovieById()throws MovieNotFoundException
	{
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		final Movie movie1=movieServiceImpl.getMovieById(1);
		assertEquals("Fetching movie by id failed",movie1,movie);
		verify(movieRepository,times(1)).findById(movie.getId());	
	}
	
	@Test
	public void testGetAllMovies()throws MovieNotFoundException
	{
		List<Movie> movieList=new ArrayList<Movie>(1);
		
		when(movieRepository.findByUserId("jhabrajesh")).thenReturn(movieList);
		final List<Movie> movieList1=movieServiceImpl.getMyMovies("jhabrajesh");
		assertEquals("Fetching all movies failed",movieList,movieList1);
		verify(movieRepository,times(1)).findByUserId("jhabrajesh");	
	}
	


}
