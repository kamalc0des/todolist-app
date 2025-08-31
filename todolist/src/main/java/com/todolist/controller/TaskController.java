package com.todolist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.todolist.mapper.TaskMapper;
import com.todolist.model.User;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;

/**
 * TaskController is the REST controller for Task resources.
 * It defines the API endpoints and maps HTTP requests to service methods.
 *
 * Annotations:
 * - @RestController: Marks the class as a REST controller (returns JSON by
 * default).
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
public class TaskController {

    private final TaskService service;
    private final UserRepository userRepository;

    public TaskController(TaskService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    /**
     * Get all tasks for the authenticated user
     */
    @GetMapping
    public List<TaskDto> getAllTasks(Authentication authentication) {
        return service.getAllTasks(authentication.getName());
    }

    /**
     * Add a task for the authenticated user
     */
    @PostMapping
    public TaskDto addTask(@Valid @RequestBody TaskDto dto, Authentication authentication) {
        return service.addTask(dto, authentication.getName());
    }

    /**
     * Update a task, ensuring it belongs to the authenticated user
     */
    @PutMapping("/{id}")
    public TaskDto updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDto dto,
            Authentication authentication) {
                User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return service.updateTask(id, dto, user);
    }

    /**
     * Delete a task, ensuring it belongs to the authenticated user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
        .orElseThrow(() -> new RuntimeException("User not found"));
        service.deleteTask(id, user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get a single task by ID (only if owned by the user)
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id, Authentication authentication) {
        return service.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}