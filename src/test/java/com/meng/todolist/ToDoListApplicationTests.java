package com.meng.todolist;

import com.meng.todolist.entity.Task;
import com.meng.todolist.mapper.TaskMapper;
import com.meng.todolist.service.ITaskService;
import com.meng.todolist.service.serviceImpl.ITaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ToDoListApplicationTests {
    @Autowired
    TaskMapper taskMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void testSelectAll() {
        List<Task> allTask = taskMapper.getAllTask();
        for (Task task: allTask) {
            System.out.println(task);
        }
    }
    @Test
    void testDate() {
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date);
    }
    @Test
    void testInsert() {
        Task task = new Task();
        task.setName("你永远不知道下一块是什么");
        task.setStatus(false);
        task.setStartTime(new Date());
        task.setEndTime(new Date());
        System.out.println(task.toString());
        taskMapper.insertTask(task);
        Integer taskId = task.getTaskId();
        System.out.println(taskId);
    }

}
