package com.todolist.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; // allows pre-build methods ready to be used: CRUD (used in TaskServive.java)
import com.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
