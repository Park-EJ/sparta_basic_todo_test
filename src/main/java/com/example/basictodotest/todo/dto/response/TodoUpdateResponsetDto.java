package com.example.basictodotest.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoUpdateResponsetDto {

    private final String content;

    public TodoUpdateResponsetDto(String content) {
        this.content = content;
    }
}
