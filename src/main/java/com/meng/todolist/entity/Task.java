package com.meng.todolist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Task {
    private Integer taskId;
    private String name;
    private boolean status;
    private Date startTime;
    private Date endTime;
}
