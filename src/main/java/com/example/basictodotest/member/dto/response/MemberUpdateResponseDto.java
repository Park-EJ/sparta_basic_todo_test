package com.example.basictodotest.member.dto.response;

import lombok.Getter;

@Getter
public class MemberUpdateResponseDto {

    private final String name;

    public MemberUpdateResponseDto(String name) {
        this.name = name;
    }
}
