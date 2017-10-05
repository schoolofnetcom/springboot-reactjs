package com.schoolofnet.spring_react.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.schoolofnet.spring_react.model.User;
import com.schoolofnet.spring_react.repository.UserRepository;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
	}

}
