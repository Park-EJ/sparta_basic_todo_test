package com.example.basictodotest.member.dto.response;

import lombok.Getter;

@Getter
public class MemberFindResponseDto {

    private final String name;
    private final String email;

    public MemberFindResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
