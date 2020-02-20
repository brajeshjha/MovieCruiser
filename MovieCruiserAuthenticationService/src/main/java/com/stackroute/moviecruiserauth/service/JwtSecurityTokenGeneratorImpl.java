package com.stackroute.moviecruiserauth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserauth.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator{

	public JwtSecurityTokenGeneratorImpl() {
	}

	@Override
	public Map<String, String> generateTokens(User user) {
		// TODO Auto-generated method stub
		
		String jwtToken=null;
		
		jwtToken = Jwts.builder()
				.setSubject(user.getUserId())
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact();
		
		Map<String,String> map=new HashMap<>();
		map.put("token", jwtToken);
		map.put("message","succesfully logged in");
		return map;
	}

}
