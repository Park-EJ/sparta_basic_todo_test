package com.example.basictodotest.todo.entity;

import com.example.basictodotest.common.entity.BaseEntity;
import com.example.basictodotest.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "todos")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Todo(String content, Member member) {
        this.content = content;
        this.member = member;
    }

    public void update(String content) {
        this.content = content;
    }
}
