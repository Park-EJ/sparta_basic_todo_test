package com.example.basictodotest.todo.repository;

import com.example.basictodotest.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
