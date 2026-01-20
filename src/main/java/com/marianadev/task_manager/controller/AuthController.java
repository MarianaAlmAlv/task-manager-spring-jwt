package com.marianadev.task_manager.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marianadev.task_manager.dto.userDto;
import com.marianadev.task_manager.security.model.AuthResponse;
import com.marianadev.task_manager.service.AuthService;

/**
 * AuthController handle the Authentication for application 
 * (Authentication with Bearer JWT) 
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService= authService;
    }

    /**
     * Authentication of user by username and password
     * @param userDto request (username, password)
     * @return AuthenticationResponse with String token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody userDto userDto) {
        AuthResponse response = authService.login(userDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Create and authenticate a new user 
     * @param userDto request (username, password, name, lastname)
     * @return AuthenticationResponse with String token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody userDto userDto) {
        AuthResponse response = authService.register(userDto);
        return ResponseEntity.ok(response);
    }
}