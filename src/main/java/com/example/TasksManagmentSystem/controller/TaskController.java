package com.example.TasksManagmentSystem.controller;

import com.example.TasksManagmentSystem.models.Task;
import com.example.TasksManagmentSystem.models.TaskStatus;
import com.example.TasksManagmentSystem.models.User;
import com.example.TasksManagmentSystem.service.TaskService;
import com.example.TasksManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listTasks(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<Task> tasks = taskService.findByUser(user);
        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        List<User> allUsers = userService.findAll();
        List<TaskStatus> allStatuses = Arrays.asList(TaskStatus.values());
        model.addAttribute("task", new Task());
        model.addAttribute("allStatuses", allStatuses);
        return "task/form";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) throws Exception {
        Optional<Task> taskOptional = taskService.findById(id);
        List<User> allUsers = userService.findAll();
        List<TaskStatus> allStatuses = Arrays.asList(TaskStatus.values());

        if (taskOptional.isPresent()) {
            model.addAttribute("task", taskOptional.get());
            model.addAttribute("allStatuses", allStatuses);
            return "task/form";
        } else {
            throw new Exception("Could not find task");
        }
    }


    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task, Principal principal) {
        taskService.saveOrUpdate(task, principal);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}

