package com.meng.todolist.service;

import com.meng.todolist.entity.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAllTask();

    void insertTask(Task task);
}
