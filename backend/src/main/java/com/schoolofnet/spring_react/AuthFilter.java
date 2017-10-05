package com.schoolofnet.spring_react;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolofnet.spring_react.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public AuthFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		try {
			User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							user.getUsername(), user.getPassword(), new ArrayList<>()));
			
		} catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain filter, Authentication auth) {

    	Calendar calendar = Calendar.getInstance();
    			
    	calendar.add(Calendar.DATE, 1);
    	
    	Date expTime = calendar.getTime();
    	
    	String token = Jwts.builder()
    				.setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
    				.setExpiration(expTime)
    				.signWith(SignatureAlgorithm.HS512, "MY_SECRET_KEY_101234564")
    				.compact();
    	
    	res.addHeader("Authorization", "Bearer " + token);
    }
}
