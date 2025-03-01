package com.example.basictodotest.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoFindResponseDto {

    private final String content;

    public TodoFindResponseDto(String content) {
        this.content = content;
    }
}
