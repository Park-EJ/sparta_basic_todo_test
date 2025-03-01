package com.example.basictodotest.todo.service;

import com.example.basictodotest.member.entity.Member;
import com.example.basictodotest.member.repository.MemberRepository;
import com.example.basictodotest.todo.dto.request.TodoSaveRequestDto;
import com.example.basictodotest.todo.dto.request.TodoUpdateRequestDto;
import com.example.basictodotest.todo.dto.response.TodoFindResponseDto;
import com.example.basictodotest.todo.dto.response.TodoSaveResponseDto;
import com.example.basictodotest.todo.dto.response.TodoUpdateResponsetDto;
import com.example.basictodotest.todo.entity.Todo;
import com.example.basictodotest.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;


    // 일정 저장
    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "멤버가 아닙니다."));

        Todo todo = new Todo(dto.getContent(), member);

        Todo saved = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                saved.getId(),
                saved.getMember().getId(),
                saved.getMember().getName(),
                saved.getContent());
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<TodoFindResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(todo -> new TodoFindResponseDto(todo.getContent())).toList();
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public TodoFindResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다."));

        return new TodoFindResponseDto(todo.getContent());

    }

    // 일정 수정
    @Transactional
    public TodoUpdateResponsetDto update(Long memberId, Long todoId, TodoUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "멤버가 아닙니다."));

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다."));

        todo.update(dto.getContent());

        return new TodoUpdateResponsetDto(todo.getContent());
    }

    // 일정 삭제
    @Transactional
    public void delete(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "멤버가 아닙니다."));

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다."));

        todoRepository.deleteById(todoId);
    }
}
