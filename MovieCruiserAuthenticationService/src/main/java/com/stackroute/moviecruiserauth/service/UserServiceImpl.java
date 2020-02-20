package com.stackroute.moviecruiserauth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserauth.exception.UserAlreadyExistException;
import com.stackroute.moviecruiserauth.exception.UserNotFoundException;
import com.stackroute.moviecruiserauth.domain.User;
import com.stackroute.moviecruiserauth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	private final transient UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		super();
		this.userRepository=userRepository;
		
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(user.getUserId());
		if(optionalUser.isPresent())
		{
			throw new UserAlreadyExistException("User details cannot be saved . User already exist");
		}
			userRepository.save(user);
			return true;

	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByUserIdAndPassword(userId, password);
		
		if(user==null)
		{
			throw new UserNotFoundException("User details mismatch");
		}
		return user;
	}

}
