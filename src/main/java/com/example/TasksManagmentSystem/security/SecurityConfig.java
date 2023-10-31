package com.example.TasksManagmentSystem.security;

import com.example.TasksManagmentSystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        System.out.println("UserDetailsService class: " + this.userDetailsService.getClass());
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) ->
                    authorizeRequests
                            .requestMatchers("/login", "/resources/**", "/registration", "/error").permitAll()
                            .requestMatchers("/").authenticated()
                            .requestMatchers("/users/**").hasRole("ADMIN")
                            .requestMatchers("/tasks/**").hasRole("USER")
                            .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                    formLogin
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/tasks")
                            .permitAll()
            )
            .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(exceptionHandling ->
            exceptionHandling
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        throw new AccessDeniedException("Access Denied");
                    })
        );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}




