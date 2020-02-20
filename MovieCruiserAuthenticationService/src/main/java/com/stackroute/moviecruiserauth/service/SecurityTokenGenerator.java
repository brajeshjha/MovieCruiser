package com.stackroute.moviecruiserauth.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserauth.domain.User;


public interface SecurityTokenGenerator {
	
	Map<String,String> generateTokens(User user);

}
