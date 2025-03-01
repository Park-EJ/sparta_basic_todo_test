package com.example.basictodotest.auth.controller;

import com.example.basictodotest.auth.dto.request.AuthLoginRequestDto;
import com.example.basictodotest.auth.dto.request.AuthSignupRequestDto;
import com.example.basictodotest.auth.dto.response.AuthLoginResponseDto;
import com.example.basictodotest.auth.service.AuthService;
import com.example.basictodotest.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody AuthSignupRequestDto dto) {
        authService.signup(dto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "회원가입했습니다.");

        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthLoginRequestDto dto, HttpServletRequest request) {
        AuthLoginResponseDto result = authService.login(dto);

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "로그인했습니다.");

        return ResponseEntity.ok(response);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "로그아웃했습니다.");

        return ResponseEntity.ok(response);
    }

}