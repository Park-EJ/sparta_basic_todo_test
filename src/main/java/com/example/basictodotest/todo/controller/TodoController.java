package com.example.basictodotest.todo.controller;

import com.example.basictodotest.common.consts.Const;
import com.example.basictodotest.todo.dto.request.TodoSaveRequestDto;
import com.example.basictodotest.todo.dto.request.TodoUpdateRequestDto;
import com.example.basictodotest.todo.dto.response.TodoFindResponseDto;
import com.example.basictodotest.todo.dto.response.TodoSaveResponseDto;
import com.example.basictodotest.todo.dto.response.TodoUpdateResponsetDto;
import com.example.basictodotest.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 일정 생성(로그인 상태일 경우만 가능)
    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveRequestDto dto) {
        return ResponseEntity.ok(todoService.save(memberId, dto));
    }

    // 일정 전체 조회
    @GetMapping("/todos")
    public ResponseEntity<List<TodoFindResponseDto>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    // 일정 단건 조회
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoFindResponseDto> findById(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    // 일정 수정(로그인 상태일 경우만 가능)
    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponsetDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto dto) {
        return ResponseEntity.ok(todoService.update(memberId, todoId, dto));
    }

    // 일정 삭제(로그인 상태일 경우만 가능)
    @DeleteMapping("/todos/{todoId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId) {
        todoService.delete(memberId, todoId);
    }
}
