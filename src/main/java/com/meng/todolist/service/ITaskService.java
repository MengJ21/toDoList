package com.meng.todolist.service;

import com.meng.todolist.entity.Task;
import com.meng.todolist.entity.dto.TaskDDL;

import java.text.ParseException;
import java.util.List;

public interface ITaskService {
    /*
     * 查询所有任务。
     **/
    List<Task> findAllTask(String uId);

    /*
     * 插入新的任务
     **/
    void insertTask(Task task);
    /*
     * 根据日期查询任务
     **/
    List<Task> findAllTaskByDate(String date, String uId);
    /*
     * 删除任务
     **/
    int deleteTask(int taskId);
    /*
     * 更改任务状态
     **/
    void updateStatus(boolean status, int taskId);

    void updateTask(Task task);

    /**
     * 获取过去一年任务完成情况。
     * @param
     * @param
     * @return
     */
    Integer getLastYearTaskCondition(String uId);
    /**
     * 获取过去一个月任务完成情况。
     * @param
     * @param
     * @return
     */
    Integer getLastMonthTaskCondition(String uId);
    /**
     * 获取过去一周任务完成情况。
     * @param
     * @param
     * @return
     */
    Integer getLastWeekTaskCondition(String uId);

    List<Task> getTaskTomm(String date, String uId) throws ParseException;

    List<Task> getTaskToday(String date, String uId);

    TaskDDL getTaskDDL(String uId) throws ParseException;

    void updateTaskStatus();
}
