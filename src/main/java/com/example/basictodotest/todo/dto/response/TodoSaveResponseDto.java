package com.example.basictodotest.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final Long memberId;
    private final String memberName;
    private final String content;

    public TodoSaveResponseDto(Long id, Long memberId, String memberName, String content) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.content = content;
    }
}
