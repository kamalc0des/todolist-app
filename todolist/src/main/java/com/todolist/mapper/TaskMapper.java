package com.todolist.mapper;

import com.todolist.dto.TaskDto;
import com.todolist.model.Task;
import com.todolist.model.User;

/**
 * TaskMapper is responsible for converting between:
 * - Task (the database entity)
 * - TaskDto (the DTO exposed via the API)
 * 
 * Used inside TaskService on update/create/get
 *
 * Why a Mapper?
 * - Keeps a clear separation between persistence layer (entities) and API layer (DTOs).
 * - Avoids leaking internal database structure to the frontend.
 */
public class TaskMapper {

    // Convert entity -> DTO
    public static TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.isCompleted(),
            task.getUser().getUsername()
        );
    }

    // Convert DTO -> entity
    public static Task toEntity(TaskDto dto, User user) {
        Task task = new Task();
        task.setId(dto.id());
        task.setTitle(dto.title());
        task.setCompleted(dto.completed());
        task.setUser(user);
        return task;
    }
}
