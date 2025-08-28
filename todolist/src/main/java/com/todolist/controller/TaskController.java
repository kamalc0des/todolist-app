package com.todolist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.service.TaskService;
import jakarta.validation.Valid;
import com.todolist.dto.TaskDto;


/**
 * TaskController is the REST controller for Task resources.
 * It defines the API endpoints and maps HTTP requests to service methods.
 *
 * Annotations:
 * - @RestController: Marks the class as a REST controller (returns JSON by default).
 * - @RequestMapping("/api/tasks"): Base path for all Task endpoints.
 * - @CrossOrigin: Allows requests from the Angular frontend (CORS).
 *
 * Example routes:
 * - GET /api/tasks -> get all tasks
 * - GET /api/tasks/{id} -> get a single task
 * - POST /api/tasks -> create a new task
 * - PUT /api/tasks/{id} -> update an existing task
 * - DELETE /api/tasks/{id} -> delete a task
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200") // authorize Angular
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public TaskDto addTask(@Valid @RequestBody TaskDto dto) {
        return service.addTask(dto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto dto) {
        return service.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return service.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
