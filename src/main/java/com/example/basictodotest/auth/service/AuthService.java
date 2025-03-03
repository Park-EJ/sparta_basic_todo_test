package com.example.basictodotest.auth.service;

import com.example.basictodotest.auth.dto.request.AuthLoginRequestDto;
import com.example.basictodotest.auth.dto.request.AuthSignupRequestDto;
import com.example.basictodotest.auth.dto.response.AuthLoginResponseDto;
import com.example.basictodotest.common.config.PasswordEncoder;
import com.example.basictodotest.common.exception.InvalidCredentialException;
import com.example.basictodotest.member.entity.Member;
import com.example.basictodotest.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,  "이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = new Member(dto.getEmail(), encodedPassword, dto.getName());

        Member saved = memberRepository.save(member);
    }

    // 로그인
    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new InvalidCredentialException("해당 이메일이 없습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new InvalidCredentialException("비밀번호가 일치하지 않습니다.");
        }

        return new AuthLoginResponseDto(member.getId());
    }
}
