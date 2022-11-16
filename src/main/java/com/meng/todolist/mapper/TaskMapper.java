package com.meng.todolist.mapper;

import com.meng.todolist.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    /*\
    查询所有任务
     */
    List<Task> getAllTask();
    /*
     * 根据日期查询所有任务。
     **/
    List<Task> getAllTaskByDate(String date);
    /*
    根据id删除任务
     */
    int deleteTaskById(int taskId);
    /*
    新增任务。
     */
    void insertTask(@Param("task") Task task);
    /*
     * 改变任务的状态（完成或者未完成）
     **/
    void setStatus(boolean status, int taskId);
}
