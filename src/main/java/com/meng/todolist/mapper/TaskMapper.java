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
    根据id删除任务
     */
    int deleteTaskById();
    /*
    新增任务。
     */
    void insertTask(@Param("task") Task task);
}
