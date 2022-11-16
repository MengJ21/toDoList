package com.meng.todolist.service;

import com.meng.todolist.entity.Task;

import java.util.List;

public interface ITaskService {
    /*
     * 查询所有任务。
     **/
    List<Task> findAllTask();

    /*
     * 插入新的任务
     **/
    void insertTask(Task task);
    /*
     * 根据日期查询任务
     **/
    List<Task> findAllTaskByDate(String date);
    /*
     * 删除任务
     **/
    int deleteTask(int taskId);
    /*
     * 更改任务状态
     **/
    void updateStatus(boolean status, int taskId);
}
