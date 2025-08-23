package com.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.dto.TaskDto;
import com.todolist.mapper.TaskMapper;
import com.todolist.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskDto> getAllTasks() {
        return repository.findAll().stream().map(TaskMapper::toDto).toList();
    }

    public TaskDto addTask(TaskDto dto) {
        Task task = TaskMapper.toEntity(dto);
        return TaskMapper.toDto(repository.save(task));
    }

    public TaskDto updateTask(Long id, TaskDto dto) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(dto.title());
        task.setCompleted(dto.completed());
        return TaskMapper.toDto(repository.save(task));
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public Optional<TaskDto> getTaskById(Long id) {
        return repository.findById(id)
                .map(TaskMapper::toDto);
    }
}
