package com.schoolofnet.spring_react.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schoolofnet.spring_react.model.Task;
import com.schoolofnet.spring_react.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TasksController {
	
	private TaskRepository taskRepository;
	
	public TasksController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	//http://localhost:8080/tasks/
	@PostMapping
	public void create(@RequestBody Task task) {
		taskRepository.save(task);
	}
	
	//http://localhost:8080/tasks/
	@GetMapping
	@ResponseBody
	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
	//http://localhost:8080/tasks/123456
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		taskRepository.delete(id);
	}
}
