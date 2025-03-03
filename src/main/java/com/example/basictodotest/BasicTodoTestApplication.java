package com.example.basictodotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BasicTodoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicTodoTestApplication.class, args);
    }

}
