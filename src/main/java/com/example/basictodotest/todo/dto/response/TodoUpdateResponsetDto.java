package com.example.basictodotest.todo.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoUpdateResponsetDto {

    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public TodoUpdateResponsetDto(String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
