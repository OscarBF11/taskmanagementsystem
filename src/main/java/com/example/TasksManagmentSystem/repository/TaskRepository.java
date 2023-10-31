package com.example.TasksManagmentSystem.repository;

import com.example.TasksManagmentSystem.models.Task;
import com.example.TasksManagmentSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}

