package com.stackroute.moviecruiserauth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.moviecruiserauth.exception.UserAlreadyExistException;
import com.stackroute.moviecruiserauth.exception.UserNotFoundException;
import com.stackroute.moviecruiserauth.domain.User;
import com.stackroute.moviecruiserauth.service.SecurityTokenGenerator;
import com.stackroute.moviecruiserauth.service.UserService;

@RestController
@EnableWebMvc
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;
	

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		System.out.println(user.getUserId());
		ResponseEntity<?> responseEntity;
		try {
			userService.saveUser(user);
			responseEntity =new ResponseEntity<String>("User Registerd successfully",HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return responseEntity;
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetails)
	{
		ResponseEntity<?> responseEntity;
		try{
			String userId=loginDetails.getUserId();
			String password=loginDetails.getPassword();
			
			if(userId==null||password==null)
			{
				throw new Exception ("Username or password cannot be empty");
			}
			
			User user=userService.findByUserIdAndPassword(userId, password);
			
			if(!userId.equals(user.getUserId()))
			{
				throw new Exception ("User with given id does not exist");
			}
			
			if(!password.equals(user.getPassword()))
			{
				throw new Exception ("Invalid login creditials.Please check username and password");
			}
			
			Map<String,String> map=securityTokenGenerator.generateTokens(user);
			responseEntity = new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
			
		}
		catch(UserNotFoundException e)
		{
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
		
		return responseEntity;
	}

}
