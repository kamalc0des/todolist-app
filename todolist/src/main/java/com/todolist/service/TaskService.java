package com.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.dto.TaskDto;
import com.todolist.mapper.TaskMapper;
import com.todolist.repository.TaskRepository;


/**
 * TaskService contains the business logic for managing tasks.
 *
 * Responsibilities:
 * - Communicates with TaskRepository (database access).
 * - Converts between entities (Task) and DTOs (TaskDto).
 * - Enforces rules (e.g., throw exception if task not found).
 *
 * Why separate from Controller?
 * - Keeps controllers focused on HTTP layer (requests/responses).
 * - Makes the code easier to test (service can be unit-tested independently).
 */
@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all tasks.
     */
    public List<TaskDto> getAllTasks() {
        return repository.findAll().stream().map(TaskMapper::toDto).toList();
    }

    /**
     * Retrieve a task by its ID.
     * Throws RuntimeException if not found -> handled by GlobalExceptionHandler.
     */
    public Optional<TaskDto> getTaskById(Long id) {
        return repository.findById(id)
                .map(TaskMapper::toDto);
    }

    /**
     * Create a new task.
     */
    public TaskDto addTask(TaskDto dto) {
        Task task = TaskMapper.toEntity(dto);
        return TaskMapper.toDto(repository.save(task));
    }


    /**
     * Update an existing task.
     * If not found -> RuntimeException is thrown.
     */
    public TaskDto updateTask(Long id, TaskDto dto) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(dto.title());
        task.setCompleted(dto.completed());
        return TaskMapper.toDto(repository.save(task));
    }

    /**
     * Delete a task by its ID.
     */
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
