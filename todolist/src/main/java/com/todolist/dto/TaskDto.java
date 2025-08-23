// Used by the frontend, we do not expose directly our datas from Task entity

package com.todolist.dto;

public record TaskDto(Long id, String title, boolean completed) {} 
