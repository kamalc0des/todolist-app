package com.todolist.service;

import com.todolist.dto.TaskDto;
import com.todolist.mapper.TaskMapper;
import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskService service;
    private User user;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        userRepository = mock(UserRepository.class);
        service = new TaskService(taskRepository, userRepository);

        // Fake user for testing
        user = User.builder()
                .id(1L)
                .username("testUser")
                .password("1234")
                .build();

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
    }

    @Test
    void shouldReturnAllTasks() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setCompleted(false);
        task.setUser(user);

        when(taskRepository.findByUser(user)).thenReturn(List.of(task));

        List<TaskDto> tasks = service.getAllTasks("testUser");

        assertThat(tasks).hasSize(1);
        assertThat(tasks.get(0).title()).isEqualTo("Test Task");
        assertThat(tasks.get(0).username()).isEqualTo("testUser");
    }

    @Test
    void shouldReturnTaskById() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");
        task.setCompleted(true);
        task.setUser(user);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<TaskDto> result = service.getTaskById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().title()).isEqualTo("Sample Task");
        assertThat(result.get().completed()).isTrue();
        assertThat(result.get().username()).isEqualTo("testUser");
    }

    @Test
    void shouldThrowWhenTaskNotFoundOnUpdate() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        TaskDto updateDto = new TaskDto(99L, "Updated", false, "testUser");

        assertThrows(RuntimeException.class, () -> service.updateTask(99L, updateDto, user));
    }

    @Test
    void shouldAddTask() {
        TaskDto dto = new TaskDto(null, "New Task", false, "testUser");
        Task entity = TaskMapper.toEntity(dto, user);
        entity.setId(1L);

        when(taskRepository.save(any(Task.class))).thenReturn(entity);

        TaskDto result = service.addTask(dto, "testUser");

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.title()).isEqualTo("New Task");
        assertThat(result.completed()).isFalse();
        assertThat(result.username()).isEqualTo("testUser");
    }
}
