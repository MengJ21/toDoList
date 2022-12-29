package com.meng.todolist.mapper;

import com.meng.todolist.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    /*
    直接查询所有任务。
     */
    List<Task> getAllTasks();
    /*\
        根据id查询所有任务
     */
    List<Task> getAllTask(String uId);
    /*
     * 根据日期查询所有任务。
     **/
    List<Task> getAllTaskByDate(String date, String uId);
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

    void setExpired(int taskId);
    void updateTask(Task task);

    Task getTaskByTaskId(String taskId);

    /**
     * 获取过去一周已完成的任务数量。
     * @return
     */
    Integer getLastWeekTaskCompleted(String uId);    /**
     * 获取过去一周未完成的任务数量。
     * @return
     */
    Integer getLastWeekTaskUnCompleted(String uId);
    /**
     * 获取过去一年已完成的任务数量。
     * @return
     */
    Integer getLastYearCompleted(String uId);    /**
     * 获取过去一年未完成的任务数量。
     * @return
     */
    Integer getLastYearTaskUnCompleted(String uId);    /**
     * 获取过去一个月已完成的任务数量。
     * @return
     */
    Integer getLastMonthTaskCompleted(String uId);    /**
     * 获取过去一个月未完成的任务数量。
     * @return
     */
    Integer getLastMonthTaskUnCompleted(String uId);

    List<Task> getEndTimeTasks(String endTime, String uId);


}
