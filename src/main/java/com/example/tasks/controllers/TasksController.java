package com.example.tasks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasks.entities.Task;
import com.example.tasks.service.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {

	private final TasksService tasksService;

	public TasksController(TasksService tasksService) {
		this.tasksService = tasksService;
	}

	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		try {
			Task createdTask = tasksService.addTask(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = tasksService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody Task updateTask) {
		try {
			Optional<Task> existingTask = tasksService.getTaskById(taskId);

			if (existingTask.isPresent()) {

				Task taskToUpdate = existingTask.get();
				taskToUpdate.setTitle(updateTask.getTitle());
				taskToUpdate.setStatus(updateTask.getStatus());
				taskToUpdate.setDescription(updateTask.getDescription());

				Task updatedTask = tasksService.updateTask(taskToUpdate);
				return ResponseEntity.ok(updatedTask);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Tarefa não encontrada
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> removeTask(@PathVariable Integer taskId) {
		try {
			Optional<Task> existingTask = tasksService.getTaskById(taskId);

			if (existingTask.isPresent()) {
				tasksService.removeTask(existingTask.get());
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
