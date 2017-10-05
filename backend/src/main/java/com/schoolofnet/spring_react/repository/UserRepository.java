package com.schoolofnet.spring_react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofnet.spring_react.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
