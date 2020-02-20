package com.stackroute.moviecruiserserverapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapp.MovieCruiserServerAppApplication;
import com.stackroute.moviecruiserserverapp.controller.MovieController;
import com.stackroute.moviecruiserserverapp.domain.Movie;
import com.stackroute.moviecruiserserverapp.service.MovieService;
import com.stackroute.moviecruiserserverapp.MovieCruiserServerAppApplication;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@ContextConfiguration(classes=MovieCruiserServerAppApplication.class)
public class MovieControllerTest {

	public MovieControllerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient MovieService movieService;
	
	@InjectMocks
	private MovieController movieController;
	
	private Movie movie;
	private String token;
	private List<Movie> movieList=new ArrayList<Movie>();
	
	@Before
	public void setUp()
	{
		movie=new Movie(1,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh");
		movieList.add(movie);
		movie=new Movie(2,12345,"Dark Knight","Wonderful","www.sample.com","2014-05-01",45.5,102,"jhabrajesh");
		movieList.add(movie);	
		token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaGFicmFqZXNoIiwiaWF0IjoxNTU2MTM0OTI3fQ.Th8-w8qVGv1Ru72Sie6QeQIPDxxZ7y_eQSndkvCLAck";
	}
	
	@Test
	public void testSaveNewMovieSuccess()throws Exception
	{
		when(movieService.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/movie/").header("Authorization",token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated());
		verify(movieService,times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}
	
	@Test
	public void testUpdateMovieSuccess()throws Exception
	{
		movie.setComments("beautiful");
		when(movieService.updateMovie(movie)).thenReturn(movieList.get(0));
		mvc.perform(put("/api/movie/").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk()).andDo(print());
		verify(movieService,times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}
	
	@Test
	public void testDeleteMovieById()throws Exception
	{
		when(movieService.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/movie/{id}",1).header("Authorization", token)).andExpect(status().isOk());
		verify(movieService,times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
	}
	
	
	@Test
	public void testFetchMovieById()throws Exception
	{
		when(movieService.getMovieById(1)).thenReturn(movieList.get(0));
		mvc.perform(get("/api/movie/{id}",1).header("Authorization", token)).andExpect(status().isOk());
		verify(movieService,times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieService);
	}
	
	@Test
	public void testGetAllMovies()throws Exception
	{
		when(movieService.getMyMovies("jhabrajesh")).thenReturn(movieList);
		mvc.perform(get("/api/movie/").header("Authorization", token)).andExpect(status().isOk()).andDo(print());
		verify(movieService,times(1)).getMyMovies("jhabrajesh");
		verifyNoMoreInteractions(movieService);
	}
	
	
	private static String jsonToString(final Object obj)throws JsonProcessingException
	{
		String result;
		try{
			final ObjectMapper mapper=new ObjectMapper();
			final String  jsonContent=mapper.writeValueAsString(obj);
			result=jsonContent;
		}
		catch(JsonProcessingException e)
		{
			result="JsonProcessingException";
		}
		return  result;
	}
	
	

}


