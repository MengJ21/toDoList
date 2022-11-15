package com.meng.todolist.controller;


import com.meng.todolist.entity.Task;
import com.meng.todolist.model.vo.Result;
import com.meng.todolist.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ITaskService iTaskService;
    /*
    获取所有任务。
     */
    @GetMapping("/getAllTask")
    public Result getAllTask() {
        return Result.ok("查询所有任务成功", iTaskService.findAllTask());
    }
    /*
    插入一个任务
     */
    @PostMapping("insertTask")
    public Result insertTask(@RequestBody Task task) {
        iTaskService.insertTask(task);
        return Result.ok("插入成功");
    }
}
