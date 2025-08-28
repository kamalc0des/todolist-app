// Used by the frontend, we do not expose directly our datas from Task entity

package com.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskDto(
    Long id,
    @NotBlank(message = "The title is mandatory, please enter it")
    @Size(min = 3, max = 100, message = "The title must be more than 3 characters and less than 100")
    String title,
    boolean completed
) {}
