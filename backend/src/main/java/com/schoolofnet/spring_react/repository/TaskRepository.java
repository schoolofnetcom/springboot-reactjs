package com.schoolofnet.spring_react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofnet.spring_react.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
