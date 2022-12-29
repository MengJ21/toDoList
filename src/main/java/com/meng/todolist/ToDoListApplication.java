package com.meng.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

}
