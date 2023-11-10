package com.example.tasks.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.tasks.entities.Task;
import com.example.tasks.entities.enums.TaskStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private TasksService tasksService;

	@Test
	public void testCreateTest() {
		// Cria uma tarefa de test
		Task task = new Task();
		task.setTitle("Tarefa de Test");
		task.setDescription("Descrição test");
		task.setStatus(TaskStatus.TODO);

		Task savedTask = tasksService.addTask(task);

		assertNotNull(savedTask.getId());
		assertEquals("Tarefa de Test", savedTask.getTitle());
		assertEquals("Descrição test", savedTask.getDescription());
	}

	@Test
	public void testUpdateTask() {
		// Criar uma nova tarefa para ser atualizada
		Task task = new Task();
		task.setTitle("Tarefa de Test");
		task.setDescription("Descrição test");
		task.setStatus(TaskStatus.TODO);

		// Salvar a tarefa no banco de dados
		Task savedTask = tasksService.addTask(task);

		// Atualizar os atributos da tarefa
		savedTask.setTitle("Mudança de Título");
		savedTask.setDescription("Nova Descrição");
		savedTask.setStatus(TaskStatus.IN_PROGRESS);

		// Chamar o método de atualização no serviço
		Task updatedTask = tasksService.updateTask(task);

		// Verificar se a tarefa foi atualizada corretamente
		assertNotNull(updatedTask.getId());
		assertEquals("Mudança de Título", updatedTask.getTitle());
		assertEquals("Nova Descrição", updatedTask.getDescription());
		assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
	}

	@Test
	public void testRemoveTask() {

		Task task1 = new Task();
		task1.setTitle("Tarefa de Teste 1");
		task1.setDescription("Descrição teste 1");
		task1.setStatus(TaskStatus.TODO);

		// Salvar a tarefa no banco de dados
		Task savedTask = tasksService.addTask(task1);

		Task task2 = new Task();
		task2.setTitle("Tarefa de Teste 2");
		task2.setDescription("Descrição teste 2");
		task2.setStatus(TaskStatus.COMPLETED);

		// Salvar a tarefa no banco de dados
		Task savedTask2 = tasksService.addTask(task2);

		tasksService.removeTask(savedTask2);

		assertNotNull(savedTask.getId());
	}

	@Test
	public void testGetAllTasks() {
		Task task1 = new Task();
		task1.setTitle("Tarefa de Teste 1");
		task1.setDescription("Descrição teste 1");
		task1.setStatus(TaskStatus.TODO);

		// Salvar a tarefa no banco de dados
		Task savedTask = tasksService.addTask(task1);

		Task task2 = new Task();
		task2.setTitle("Tarefa de Teste 2");
		task2.setDescription("Descrição teste 2");
		task2.setStatus(TaskStatus.COMPLETED);

		Task savedTask2 = tasksService.addTask(task2);

			
		assertTrue(tasksService.getAllTasks().contains(savedTask2));
	}

}
