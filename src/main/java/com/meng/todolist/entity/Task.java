package com.meng.todolist.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Task {
    private Integer taskId;
    private String name;
    private boolean status;
    private Date startTime;
    private Date endTime;
    private Boolean expired;
    private Integer typeId;
    private Integer uId;
    private TaskType taskType;
    private User user;
}
