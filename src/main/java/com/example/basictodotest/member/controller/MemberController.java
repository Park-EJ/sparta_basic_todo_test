package com.example.basictodotest.member.controller;

import com.example.basictodotest.common.consts.Const;
import com.example.basictodotest.member.dto.request.MemberUpdateRequestDto;
import com.example.basictodotest.member.dto.response.MemberFindResponseDto;
import com.example.basictodotest.member.dto.response.MemberUpdateResponseDto;
import com.example.basictodotest.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 전체 조회
//    @GetMapping("/members")
//    public ResponseEntity<List<MemberFindResponseDto>> findAll() {
//        return ResponseEntity.ok(memberService.findAll());
//    }

    // 멤버 전체 조회(페이징)
    @GetMapping("/members")
    public ResponseEntity<Page<MemberFindResponseDto>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        int adjustedPage = Math.max(0, page - 1);
        Pageable pageable = PageRequest.of(adjustedPage, size, Sort.by("updatedAt").descending());

        return ResponseEntity.ok(memberService.findAll(pageable));
    }

    // 멤버 단건 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberFindResponseDto> findById(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    // 멤버 이름 수정(로그인 상태일 경우만 가능)
    @PatchMapping("/members")
    public ResponseEntity<MemberUpdateResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto dto) {
        return ResponseEntity.ok(memberService.update(memberId, dto));
    }

    // 멤버 삭제(회원탈퇴) / 로그인 상태일 경우만 가능
    @DeleteMapping("/members")
    public void delete(@SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId) {
        memberService.delete(memberId);
    }

//    // 멤버 삭제(회원탈퇴) / 로그인 상태일 경우만 가능 + 세션도 무효화하려면 아래 코드로 수정(controller만 수정)
//    // 회원 삭제 시 자동으로 로그아웃되도록 구현
//    @DeleteMapping("/members")
//    public void delete(
//            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
//            HttpSession session
//    ) {
//        memberService.delete(memberId);
//        session.invalidate();
//    }
}



