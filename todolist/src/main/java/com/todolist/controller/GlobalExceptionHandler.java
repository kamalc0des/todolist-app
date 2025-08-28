package com.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler centralizes exception handling across the whole application.
 *
 * Why use it?
 * - Keeps controllers clean by removing error handling logic from them.
 * - Ensures consistent error response format for the frontend (Angular).
 * - Improves maintainability (all error handling in one place).
 *
 * Behavior:
 * - Validation errors (@Valid) -> return {"errors": {"field": "message"}}
 * - Runtime exceptions (business errors) -> return {"errors": {"global": "message"}}
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    
    /**
     * Handle validation errors triggered by @Valid annotations.
     * Example response:
     * {
     *   "errors": {
     *     "title": "Title is mandatory"
     *   }
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handle business logic exceptions thrown in the service layer.
     * Example response:
     * {
     *   "errors": {
     *     "global": "Task not found"
     *   }
     * }
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errors", Map.of("global", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
