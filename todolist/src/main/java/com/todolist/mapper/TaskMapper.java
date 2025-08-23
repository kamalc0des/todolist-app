package com.todolist.mapper;

import com.todolist.dto.TaskDto;
import com.todolist.model.Task;

public class TaskMapper {
    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.isCompleted());
    }

    public static Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.id());
        task.setTitle(dto.title());
        task.setCompleted(dto.completed());
        return task;
    }
}
