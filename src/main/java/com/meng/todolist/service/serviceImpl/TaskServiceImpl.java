package com.meng.todolist.service.serviceImpl;

import com.meng.todolist.entity.Task;
import com.meng.todolist.entity.dto.TaskDDL;
import com.meng.todolist.mapper.TaskMapper;
import com.meng.todolist.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 任务的服务层。
 */
@Service
@Slf4j
public class TaskServiceImpl implements ITaskService {
    /*
    查询所有任务
     */
    @Autowired
    TaskMapper taskMapper;
    @Override
    public List<Task> findAllTask(String uId) {
        return taskMapper.getAllTask(uId);
    }

    @Override
    public void insertTask(Task task) {
        taskMapper.insertTask(task);
    }

    @Override
    public List<Task> findAllTaskByDate(String date, String uId) {
        return taskMapper.getAllTaskByDate(date,uId);
    }

    @Override
    public int deleteTask(int taskId) {
        return taskMapper.deleteTaskById(taskId);
    }

    @Override
    public void updateStatus(boolean status, int taskId) {
        taskMapper.setStatus(status,taskId);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public Integer getLastYearTaskCondition(String uId) {
        Integer cTask = taskMapper.getLastYearCompleted(uId);
        Integer ucTask = taskMapper.getLastYearTaskUnCompleted(uId);
        return (int)(((float)cTask / (cTask + ucTask)) * 100);
    }

    @Override
    public Integer getLastMonthTaskCondition(String uId) {
        Integer cTask = taskMapper.getLastMonthTaskCompleted(uId);
        Integer ucTask = taskMapper.getLastMonthTaskUnCompleted(uId);
        return (int)(((float)cTask / (cTask + ucTask)) * 100);
    }

    @Override
    public Integer getLastWeekTaskCondition(String uId) {
        Integer cTask = taskMapper.getLastWeekTaskCompleted(uId);
        Integer ucTask = taskMapper.getLastWeekTaskUnCompleted(uId);
        return (int)(((float)cTask / (cTask + ucTask)) * 100);
    }

    @Override
    public List<Task> getTaskTomm(String date, String uId) throws ParseException {
        // 获取当前时间。
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = dateFormat.parse(date);
        // 利用Calendar类将时间加一天。
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE,1);
        // 获取明天日期。
        Date tommorrow = calendar.getTime();
        String dateTommo = dateFormat.format(tommorrow);
        return taskMapper.getEndTimeTasks(dateTommo,uId);
    }

    @Override
    public List<Task> getTaskToday(String date, String uId) {
        return taskMapper.getEndTimeTasks(date,uId);
    }

    @Override
    public TaskDDL getTaskDDL(String uId) throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);
        List<Task> taskToday = this.getTaskToday(today, uId);
        List<Task> taskTomm = this.getTaskTomm(today, uId);
        return new TaskDDL(taskToday,taskTomm);
    }

    /**
     * 设置每天0点更新任务状态。
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void updateTaskStatus() {
        try {
            List<Task> allTasks = taskMapper.getAllTasks();
            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String nowString = dateFormat.format(now);
            Date transNow = dateFormat.parse(nowString);
            for (Task task: allTasks) {
                if (transNow.before(task.getEndTime()) || transNow.equals(task.getEndTime())) {
                    log.info("该任务没有过期。");
                } else {
                    log.info("该任务过期了。");
                    taskMapper.setExpired(task.getTaskId());
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
