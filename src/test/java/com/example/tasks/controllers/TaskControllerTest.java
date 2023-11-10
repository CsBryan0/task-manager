package com.example.tasks.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.tasks.entities.Task;
import com.example.tasks.entities.enums.TaskStatus;
import com.example.tasks.service.TasksService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TasksService tasksService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddTask() throws Exception {
        // Cria uma instância de Task para ser usada no corpo da requisição
        Task task = new Task();
        task.setTitle("Tarefa de Teste");

        // Converte a instância de Task para JSON
        String taskJson = objectMapper.writeValueAsString(task);

        // Mock do serviço para retornar a tarefa criada
        Mockito.when(tasksService.addTask(Mockito.any(Task.class))).thenReturn(task);

        // Executa a requisição POST
        ResultActions result = mockMvc.perform(post("/tasks")
                .content(taskJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Tarefa de Teste"));

        // Verifica se a tarefa retornada no corpo da resposta está correta
        Mockito.verify(tasksService, Mockito.times(1)).addTask(Mockito.any(Task.class));
    }
    
    
    @Test
    public void testGetAllTasks() throws Exception {
    	
    	List<Task> tasks = Arrays.asList(
    			new Task(1, "Titulo 1", "Descrição 1"),
    			new Task(2, "Titulo 2", "Descrição 2")
    	);
    	
    	
    	Mockito.when(tasksService.getAllTasks()).thenReturn(tasks);
    	
    	ResultActions result = mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Titulo 1"))
                .andExpect(jsonPath("$[1].title").value("Titulo 2"));
    	
    	Mockito.verify(tasksService, Mockito.times(1)).getAllTasks();
    	
    }
    
    @Test
    public void testUpdateTask() {
        // Cria um objeto de tarefa para atualização simulada
        Task updateTask = new Task();
        updateTask.setTitle("Novo Título");
        updateTask.setDescription("Nova Descrição");
        updateTask.setStatus(TaskStatus.IN_PROGRESS);

        // Cria um objeto de tarefa existente para simular a resposta do serviço
        Task existingTask = new Task();
        existingTask.setId(1);
        existingTask.setTitle("Título Antigo");
        existingTask.setDescription("Descrição Antiga");
        existingTask.setStatus(TaskStatus.TODO);

        // Cria um objeto de serviço simulado usando Mockito
        TasksService tasksServiceMock = mock(TasksService.class);

        // Configura o comportamento esperado para o método getTaskById
        when(tasksServiceMock.getTaskById(1)).thenReturn(Optional.of(existingTask));

        // Configura o comportamento esperado para o método updateTask
        when(tasksServiceMock.updateTask(any())).thenReturn(updateTask);

        // Cria uma instância do controlador injetando o serviço simulado
        TasksController taskController = new TasksController(tasksServiceMock);

        // Chama o método do controlador que você deseja testar
        ResponseEntity<Task> responseEntity = taskController.updateTask(1, updateTask);

        // Verifica se a resposta é a esperada
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Novo Título", responseEntity.getBody().getTitle());
        assertEquals("Nova Descrição", responseEntity.getBody().getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, responseEntity.getBody().getStatus());

        // Verifica se os métodos do serviço foram chamados corretamente
        verify(tasksServiceMock, times(1)).getTaskById(1);
        verify(tasksServiceMock, times(1)).updateTask(any());
    }
    
    @Test
    public void testRemoveTask(){
    	 Task existingTask = new Task();
         existingTask.setId(1);
         existingTask.setTitle("Título Antigo");
         existingTask.setDescription("Descrição Antiga");
         existingTask.setStatus(TaskStatus.TODO);
         
         
         TasksService tasksServiceMock = mock(TasksService.class);
         
         when(tasksServiceMock.getTaskById(1)).thenReturn(Optional.of(existingTask));
         
         TasksController taskController = new TasksController(tasksServiceMock);

         ResponseEntity<Void> responseEntity = taskController.removeTask(1);

         assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
         assertNull(responseEntity.getBody());

         verify(tasksServiceMock, times(1)).getTaskById(1);
         verify(tasksServiceMock, times(1)).removeTask(existingTask);
    }
    

}
