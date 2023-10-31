package com.example.TasksManagmentSystem.controller;

import com.example.TasksManagmentSystem.models.Role;
import com.example.TasksManagmentSystem.models.User;
import com.example.TasksManagmentSystem.service.RoleService;
import com.example.TasksManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("user", new User());
        return "user/form";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("allRoles", allRoles);
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}

