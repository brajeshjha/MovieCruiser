package com.stackroute.moviecruiserauth.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserauth.exception.UserAlreadyExistException;
import com.stackroute.moviecruiserauth.exception.UserNotFoundException;
import com.stackroute.moviecruiserauth.domain.User;
import com.stackroute.moviecruiserauth.repository.UserRepository;

public class UserServiceImplTest {

	public UserServiceImplTest() {
		
	}

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	public User user;

	Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("jhabrajesh", "Brajesh", "Jha", "jha12345", new Date());
		options = Optional.of(user);
	}

	@Test
	public void testSaveMovieSuccess() throws UserAlreadyExistException {
		when(userRepository.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertEquals("Cannot Register User ", true, flag);
		verify(userRepository, times(1)).save(user);
		verify(userRepository, times(1)).findById(user.getUserId());

	}

	@Test(expected = UserAlreadyExistException.class)
	public void testSaveMovieFailure() throws UserAlreadyExistException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertFalse("Cannot Register User ", flag);
		verify(userRepository, times(1)).save(user);
		verify(userRepository, times(1)).findById(user.getUserId());

	}

	@Test
	public void testValidateSuccess() throws UserAlreadyExistException, UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("jhabrajesh", userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test(expected=UserNotFoundException.class)
	public void testValidateFailure() throws  UserNotFoundException
	{
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		
	}

}
