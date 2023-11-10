package com.example.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tasks.entities.Task;
import com.example.tasks.repository.TaskRepository;

@Service
public class TasksService {

	private final TaskRepository taskRepository;

	public TasksService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

	public Task updateTask(Task task) {
		Optional<Task> existingTask = taskRepository.findById(task.getId());

		if (existingTask.isPresent()) {
			Task updateTask = existingTask.get();
			updateTask.setTitle(task.getTitle());
			updateTask.setDescription(task.getDescription());
			updateTask.setStatus(task.getStatus());
			
			
			return taskRepository.save(updateTask);
		}

		return null;
	}

	public Task removeTask(Task task) {
		Optional<Task> existingTask = taskRepository.findById(task.getId());

		if (existingTask.isPresent()) {
			taskRepository.delete(task);
			return task;
		}

		return task;
	}

	public List<Task> getAllTasks() {

		return taskRepository.findAll();
	}

	public Optional<Task> getTaskById(Integer taskId) {
		return taskRepository.findById(taskId);
		
	}
}
