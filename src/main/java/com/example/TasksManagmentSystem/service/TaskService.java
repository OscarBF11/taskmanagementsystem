package com.example.TasksManagmentSystem.service;


import com.example.TasksManagmentSystem.models.Task;
import com.example.TasksManagmentSystem.models.TaskStatus;
import com.example.TasksManagmentSystem.models.User;
import com.example.TasksManagmentSystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveOrUpdate(Task task, Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            task.setUser(user);
        }
        return saveOrUpdate(task);
    }

    public Task saveOrUpdate(Task task) {
        if (task.getId() != null) {
            Optional<Task> existingTask = taskRepository.findById(task.getId());
            if (existingTask.isPresent()) {
                Task updatedTask = existingTask.get();
                updateProperties(updatedTask, task);
                return taskRepository.save(updatedTask);
            }
        }
        if (task.getUser() == null) {
            throw new IllegalArgumentException("Task must have a user");
        }
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.PENDING);
        }
        return taskRepository.save(task);
    }



    private void updateProperties(Task existingTask, Task newTask){
        if (newTask.getTitle() != null) {
            existingTask.setTitle(newTask.getTitle());
        }
        if (newTask.getDescription() != null) {
            existingTask.setDescription(newTask.getDescription());
        }
        if (newTask.getDueDate() != null) {
            existingTask.setDueDate(newTask.getDueDate());
        }
        if (newTask.getStatus() != null) {
            existingTask.setStatus(newTask.getStatus());
        }
        if (existingTask.getUser() == null && newTask.getUser() != null) {
            existingTask.setUser(newTask.getUser());
        }
        if (existingTask.getStatus() == null && newTask.getStatus() != null) {
            existingTask.setStatus(newTask.getStatus());
        }
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

}

