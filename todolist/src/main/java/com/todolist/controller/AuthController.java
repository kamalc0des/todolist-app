package com.todolist.controller;

import com.todolist.dto.*;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * AuthController is the REST controller for User resources.
 * It defines the API endpoints and maps HTTP requests to service methods.
 *
 * Annotations:
 * - @RestController: Marks the class as a REST controller (returns JSON by default).
 * - @RequestMapping("/api/auth"): Base path for all Task endpoints.
 * - @RequiredArgsConstructor: Control that the request from the Angular frontend is correct (arguments needed)
 *
 * Example routes:
 * - POST /api/auth/register -> save a new user (username/password)
 * - POST /api/auth/login -> login a User with username/password
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}
