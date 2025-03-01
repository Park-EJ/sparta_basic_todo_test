package com.example.basictodotest.member.service;

import com.example.basictodotest.member.dto.request.MemberUpdateRequestDto;
import com.example.basictodotest.member.dto.response.MemberFindResponseDto;
import com.example.basictodotest.member.dto.response.MemberUpdateResponseDto;
import com.example.basictodotest.member.entity.Member;
import com.example.basictodotest.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 전체 조회
    @Transactional(readOnly = true)
    public List<MemberFindResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberFindResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(new MemberFindResponseDto(member.getName(), member.getEmail()));
        }
        return dtos;
    }

    // 멤버 단건 조회
    @Transactional
    public MemberFindResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 멤버가 없습니다."));

        return new MemberFindResponseDto(member.getName(), member.getEmail());
    }

    // 멤버 이름 수정
    @Transactional
    public MemberUpdateResponseDto update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 멤버가 없습니다."));

        member.update(dto.getName());

        return new MemberUpdateResponseDto(member.getName());
    }

    // 멤버 삭제
    @Transactional
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 멤버가 없습니다."));

        memberRepository.deleteById(memberId);
    }
}
