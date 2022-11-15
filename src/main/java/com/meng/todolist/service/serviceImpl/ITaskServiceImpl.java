package com.meng.todolist.service.serviceImpl;

import com.meng.todolist.entity.Task;
import com.meng.todolist.mapper.TaskMapper;
import com.meng.todolist.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 任务的服务层。
 */
@Service
public class ITaskServiceImpl implements ITaskService {
    /*
    查询所有任务
     */
    @Autowired
    TaskMapper taskMapper;
    @Override
    public List<Task> findAllTask() {
        return taskMapper.getAllTask();
    }

    @Override
    public void insertTask(Task task) {
        taskMapper.insertTask(task);
    }
}
