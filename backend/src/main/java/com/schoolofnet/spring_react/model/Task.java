package com.schoolofnet.spring_react.model;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = ThreadLocalRandom.current().nextLong(1, 500 + 1);
	
	private String name;
	private String description;
	private Boolean done = false;
	
	public Task() {}
	
	public Task(String name, String description, Boolean done) {
		this.setName(name);
		this.setDescription(description);
		this.setDone(done);
		this.setId(this.id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
}
