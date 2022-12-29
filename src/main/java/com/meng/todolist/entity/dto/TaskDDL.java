package com.meng.todolist.entity.dto;

import com.meng.todolist.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDDL {

    private List<Task> todayTask;
    private List<Task> tommoTask;

}
