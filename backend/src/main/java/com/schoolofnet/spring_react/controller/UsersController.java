package com.schoolofnet.spring_react.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schoolofnet.spring_react.model.User;
import com.schoolofnet.spring_react.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsersController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping
	@ResponseBody
	public void create(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
