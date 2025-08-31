package com.todolist.dto;

import com.todolist.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


/**
 * TaskDto is a Data Transfer Object (DTO).
 * It is used to expose only the necessary fields of Task entity
 * to the frontend (API layer) without directly exposing the database entity.
 *
 * Why use a record?
 * - Records are immutable by default and provide concise syntax.
 * - They automatically generate getters, equals, hashCode, and toString.
 * - Perfect for DTOs since they only hold data.
 */
public record TaskDto(
    Long id,
    @NotBlank(message = "The title is mandatory, please enter it")
    @Size(min = 3, max = 100, message = "The title must be more than 3 characters and less than 100")
    String title,
    boolean completed,
    String username // expose only the username, not all the object like a password, etc...
) {}
