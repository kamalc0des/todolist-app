package com.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.dto.TaskDto;
import com.todolist.mapper.TaskMapper;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;

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
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieve all tasks for a given user
     */
    public List<TaskDto> getAllTasks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(user).stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    /**
     * Retrieve a task by its ID.
     * Throws RuntimeException if not found -> handled by GlobalExceptionHandler.
     */
    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(TaskMapper::toDto);
    }

    /**
     * Create a new task.
     */
    public TaskDto addTask(TaskDto dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = TaskMapper.toEntity(dto, user);
        Task saved = taskRepository.save(task);
        return TaskMapper.toDto(saved);
    }
    

    /**
     * Update an existing task.
     * If not found -> RuntimeException is thrown.
     */
    public TaskDto updateTask(Long id, TaskDto dto, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Ensure this task belongs to this user
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed to update another user's task");
        }

        task.setTitle(dto.title());
        task.setCompleted(dto.completed());
        return TaskMapper.toDto(taskRepository.save(task));
    }

    /**
     * Delete a task by its ID.
     */
    public void deleteTask(Long id, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed to delete another user's task");
        }

        taskRepository.delete(task);
    }
}
