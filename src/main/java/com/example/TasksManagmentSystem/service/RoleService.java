package com.example.TasksManagmentSystem.service;

import com.example.TasksManagmentSystem.models.Role;
import com.example.TasksManagmentSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
