package com.example.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tasks.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
