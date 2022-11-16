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
    /*
     * 根据日期查询任务
     **/
    @GetMapping("findTaskByDate/{date}")
    public Result findTaskByDate(@PathVariable("date") String date) {
        List<Task> allTaskByDate = iTaskService.findAllTaskByDate(date);
        return Result.ok("获取" + date + "号数据成功", allTaskByDate);
    }
    /*
     * 根据id删除任务。
     **/
    @DeleteMapping("delete/{taskId}")
    public Result deleteTaskById(@PathVariable("taskId") int taskId) {
        int i = iTaskService.deleteTask(taskId);
        return Result.ok("删除成功" + i + "行受影响");
    }

    @GetMapping("setStatus/{status}/{taskId}")
    public Result setStatus(@PathVariable("status") boolean status, @PathVariable("taskId") int taskId) {
        iTaskService.updateStatus(status,taskId);
        return Result.ok("修改成功！");
    }
}
