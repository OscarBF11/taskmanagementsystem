package com.example.TasksManagmentSystem;

import com.example.TasksManagmentSystem.models.Role;
import com.example.TasksManagmentSystem.models.Task;
import com.example.TasksManagmentSystem.models.TaskStatus;
import com.example.TasksManagmentSystem.models.User;
import com.example.TasksManagmentSystem.repository.RoleRepository;
import com.example.TasksManagmentSystem.service.TaskService;
import com.example.TasksManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    RoleRepository roleRepository;

    public DataInitializer(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        crearUsuario("admin", "1234", Arrays.asList("ADMIN", "USER"));
        crearTarea("Añadir usuarios", "Añadir todos los usuarios que faltan.", new Date(), TaskStatus.PENDING, "admin");
        crearTarea("Cambiar contraseña", "Cambiar la contraseña de usuario ya que la ha olvidado.", new Date(), TaskStatus.IN_PROGRESS, "admin");

        crearUsuario("user", "1234", Arrays.asList("USER"));
        crearTarea("Adaptabilidad móvil", "Revisar la adaptabilidad móvil de la aplicación web.", new Date(), TaskStatus.PENDING, "user");
        crearTarea("Respuestas HTTP", "Revisar respuestas cuando una página no existe.", new Date(), TaskStatus.IN_PROGRESS, "user");

    }

    private void crearUsuario(String username, String password, List<String> roleNames) {
        Set<Role> roles = new HashSet<>();

        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = new Role();
                role.setName(roleName);
                role = roleRepository.save(role);
            }
            roles.add(role);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);
        userService.save(user);

        System.out.println("User " + username + " created with roles " + roles.stream().map(Role::getName).collect(Collectors.joining(", ")));
    }

    private void crearTarea(String titulo, String descripcion, Date fecha, TaskStatus estado, String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        Task tarea = new Task(null, titulo, descripcion, fecha, estado, user);
        taskService.saveOrUpdate(tarea);
        System.out.println("Tarea '" + titulo + "' creada para el usuario " + username);
    }




}

