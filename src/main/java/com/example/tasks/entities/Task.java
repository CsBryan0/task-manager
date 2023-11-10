package com.example.tasks.entities;

import java.time.LocalDate;


import com.example.tasks.entities.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private TaskStatus status;
	private LocalDate creationTask;
	
	public Task() {
		
	}
	
    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public TaskStatus getStatus() {
	    return status;
	}

	public void setStatus(TaskStatus status) {
	    this.status = status;
	}


	public LocalDate getCreationTask() {
		return creationTask;
	}

	public void setCreationTask(LocalDate creationTask) {
		this.creationTask = creationTask;
	}


	
	
}
