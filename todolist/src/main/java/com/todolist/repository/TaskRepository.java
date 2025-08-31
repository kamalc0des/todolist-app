package com.todolist.repository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; // allows pre-build methods ready to be used: CRUD (used in TaskServive.java)
import com.todolist.model.Task;
import com.todolist.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByUserId(Long userId); 
    List<Task> findByUser(User user);
}
