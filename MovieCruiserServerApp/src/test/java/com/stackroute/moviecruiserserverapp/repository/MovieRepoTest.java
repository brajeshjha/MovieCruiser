package com.stackroute.moviecruiserserverapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapp.MovieCruiserServerAppApplication;
import com.stackroute.moviecruiserserverapp.domain.Movie;
import com.stackroute.moviecruiserserverapp.repository.MovieRepository;
import com.stackroute.moviecruiserserverapp.MovieCruiserServerAppApplication;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ContextConfiguration(classes=MovieCruiserServerAppApplication.class)
@Transactional
public class MovieRepoTest {

	 @Autowired
     private transient MovieRepository movieRepo;


     public void setMovieRepository(MovieRepository movieRepo) {
             this.movieRepo = movieRepo;
     }
	
     public MovieRepoTest()
     { 
    	 
     }
	
	@Test
	public void testSaveMovie()throws Exception
	{
		movieRepo.save(new Movie(6,12300,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh"));
		Movie mov = movieRepo.findByMovieId(12300);
		final Movie movie=movieRepo.findByMovieId(mov.getMovieId());
		assertThat(movie.getMovieId()).isEqualTo(mov.getMovieId());
	}
	

	@Test
	public void testUpdateMovie()throws Exception
	{
		movieRepo.save(new Movie(6,12300,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh"));
		Movie mov = movieRepo.findByMovieId(12300);
		final Movie movie=movieRepo.findByMovieId(mov.getMovieId());		
		assertThat(movie.getTitle()).isEqualTo("Inception");
		
		movie.setComments("hello");
		movieRepo.save(movie);
		final Movie updatedMovie=movieRepo.findByMovieId(mov.getMovieId());
		Assert.assertEquals("hello",updatedMovie.getComments());
	}
	

	@Test
	public void testDeleteMovie()throws Exception
	{
		
		movieRepo.save(new Movie(6,12300,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh"));
		Movie mov = movieRepo.findByMovieId(12300);
		final Movie movie=movieRepo.findByMovieId(mov.getMovieId());		
		assertThat(movie.getTitle()).isEqualTo("Inception");
		
		movieRepo.delete(movie);
		Assert.assertEquals(Optional.empty(),movieRepo.findById(1));
	}
	
	
	@Test
	public void testGetMovie()throws Exception
	{
		Movie mov = movieRepo.save(new Movie(2,12,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh"));
		final Movie movie=movieRepo.findByMovieId(mov.getMovieId());		
		Assert.assertEquals(movie.getTitle(),"Inception");
	}
	
	
	@Test
	public void testGetAll()throws Exception
	{
		movieRepo.save(new Movie(0,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh"));
		movieRepo.save(new Movie(1,12345,"Dark Knight","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh"));
		
		final List<Movie> movieList=movieRepo.findByUserId("jhabrajesh");
		Assert.assertEquals(movieList.get(1).getTitle(),"Dark Knight");
	}
	
	

}