package com.stackroute.moviecruiserauth.service;

import com.stackroute.moviecruiserauth.exception.UserAlreadyExistException;
import com.stackroute.moviecruiserauth.exception.UserNotFoundException;
import com.stackroute.moviecruiserauth.domain.User;

public interface UserService {

	public boolean saveUser(User user)throws UserAlreadyExistException;
	
	public User findByUserIdAndPassword(String userId,String password)throws UserNotFoundException;
}
