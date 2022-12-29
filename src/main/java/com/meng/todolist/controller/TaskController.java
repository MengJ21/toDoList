package com.meng.todolist.controller;


import com.meng.todolist.config.JwtConfig;
import com.meng.todolist.entity.Task;
import com.meng.todolist.entity.dto.TaskConditionDTO;
import com.meng.todolist.entity.dto.TaskDDL;
import com.meng.todolist.model.vo.Result;
import com.meng.todolist.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    ITaskService iTaskService;
    @Autowired
    JwtConfig jwtConfig;
    /*
    获取所有任务。
     */
    @GetMapping("/getAllTask")
    public Result getAllTask(HttpServletRequest httpServletRequest) {
        String uId = String.valueOf(jwtConfig.getTokenClaim(httpServletRequest.getHeader("token")).getSubject());
        return Result.ok("查询所有任务成功", iTaskService.findAllTask(uId));
    }
    /*
    插入一个任务
     */
    @PostMapping("insertTask")
    public Result insertTask(@RequestBody Task task, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String uId = jwtConfig.getTokenClaim(token).getSubject();
        task.setUId(Integer.valueOf(uId));
        log.info("任务实体" + task.toString());
        iTaskService.insertTask(task);
        return Result.ok("插入成功");
    }

    /**
     * 修改任务
     * @param task
     * @return
     */
    @PostMapping("/updateTask")
    public Result updateTask(@RequestBody Task task) {
        iTaskService.updateTask(task);
        return Result.ok("修改成功");
    }
    /*
     * 根据日期查询任务
     **/
    @GetMapping("findTaskByDate/{date}")
    public Result findTaskByDate(@PathVariable("date") String date, HttpServletRequest httpServletRequest) {
        String uId = jwtConfig.getTokenClaim(httpServletRequest.getHeader("token")).getSubject();
        log.info("根据token获取的id：" + uId);
        List<Task> allTaskByDate = iTaskService.findAllTaskByDate(date,uId);
        return Result.ok("获取" + date + "号数据成功", allTaskByDate);
    }
    /*
     * 根据id删除任务。
     **/
    @DeleteMapping("delete/{taskId}")
    public Result deleteTaskById(@PathVariable("taskId") int taskId) {
        int i = iTaskService.deleteTask(taskId);
        return Result.ok("删除成功，" + i + "行受影响");
    }

    @GetMapping("setStatus/{status}/{taskId}")
    public Result setStatus(@PathVariable("status") boolean status, @PathVariable("taskId") int taskId) {
        iTaskService.updateStatus(status,taskId);
        return Result.ok("修改成功！");
    }

    @GetMapping("taskCondition")
    public Result getTaskCondition(HttpServletRequest httpServletRequest) {
        String uId = jwtConfig.getTokenClaim(httpServletRequest.getHeader("token")).getSubject();
        TaskConditionDTO taskCondition = new TaskConditionDTO(iTaskService.getLastYearTaskCondition(uId), iTaskService.getLastMonthTaskCondition(uId), iTaskService.getLastWeekTaskCondition(uId));
        return Result.ok("查询成功！", taskCondition);
    }
    @GetMapping("getDDL")
    public Result getDDlTask(HttpServletRequest httpServletRequest) throws ParseException {
        String uId = jwtConfig.getTokenClaim(httpServletRequest.getHeader("token")).getSubject();
        TaskDDL taskDDL = iTaskService.getTaskDDL(uId);
        return Result.ok("查询ddl成功！", taskDDL);
    }
}
