package com.meng.todolist;

import com.meng.todolist.entity.Task;
import com.meng.todolist.entity.TaskType;
import com.meng.todolist.entity.User;
import com.meng.todolist.mapper.TaskMapper;
import com.meng.todolist.mapper.TaskTypeMapper;
import com.meng.todolist.mapper.UserMapper;
import com.meng.todolist.service.ITaskService;
import com.meng.todolist.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ToDoListApplicationTests {
    @Autowired
    TaskMapper taskMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    IUserService iUserService;
    @Resource
    TaskTypeMapper taskTypeMapper;
    @Resource
    ITaskService iTaskService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectAll() {
        List<Task> allTask = taskMapper.getAllTask("1");
        for (Task task : allTask) {
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

    @Test
    void testSelectByDate() {
        List<Task> allTask = iTaskService.findAllTaskByDate("2022-11-15", "1");
        for (Task task : allTask) {
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println(task.toString());
        }
    }

    @Test
    void testDeleteById() {
        int i = taskMapper.deleteTaskById(8);
    }

    @Test
    void testUpdateTaskStatus() {
        taskMapper.setStatus(true, 1);

    }

    @Test
    void testLogin() {
        User u = userMapper.getUserByUserNameAndPassword("慕予", "011006");
        System.out.println(u.toString());
    }

    @Test
    void testToken() {
        Map<String, String> userInfo = iUserService.userLogIn("慕予", "011006");
        System.out.println(userInfo.get("token"));
    }

    @Test
    void testInsertUser() {
        User user = new User();
        user.setUsername("赵宇");
        user.setPassword("123456");
        userMapper.insertUser(user);
    }


    @Test
    void testUpdateTask() {
        Task task = taskMapper.getTaskByTaskId("1");
        TaskType taskType = task.getTaskType();
        taskTypeMapper.findTypeByTypeId("1");
    }

    @Test
    void splitDate() {
        String[] dates = "2022-11-15".strip().split("-");
        String year = dates[0];
        String lastYear = String.valueOf(Integer.parseInt(year) - 1);
        System.out.println(year);
        System.out.println(lastYear);
    }

    @Test
    void testTaskCondition() {
        Integer i1 = taskMapper.getLastWeekTaskCompleted("1");
        Integer i2 = taskMapper.getLastWeekTaskUnCompleted("1");
        Integer i3 = taskMapper.getLastMonthTaskCompleted("1");
        Integer i4 = taskMapper.getLastMonthTaskUnCompleted("1");
        Integer i5 = taskMapper.getLastYearCompleted("1");
        Integer i6 = taskMapper.getLastYearTaskUnCompleted("1");
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);
    }

    @Test
    void testTaskConditionService() {
        Integer lastMonthTaskCondition = iTaskService.getLastMonthTaskCondition("1");
        Integer lastWeekTaskCondition = iTaskService.getLastWeekTaskCondition("1");
        Integer lastYearTaskCondition = iTaskService.getLastYearTaskCondition("1");
        System.out.println(lastYearTaskCondition);
        System.out.println(lastWeekTaskCondition);
        System.out.println(lastMonthTaskCondition);
    }

    @Test
    void testDateUp() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2022-11-15");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        Date date1 = calendar.getTime();
        String date2 = dateFormat.format(date1);
        Date date3 = dateFormat.parse(date2);
        System.out.println(date2);
    }

    @Test
    void testGetDDL() throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(date);
        List<Task> taskToday = iTaskService.getTaskToday("2022-11-16", "1");
        List<Task> taskTomm = iTaskService.getTaskTomm("2022-11-16", "1");
        System.out.println("今天---------------------------------------------------------");
        for (Task task : taskToday) {
            System.out.println(task);
        }
        System.out.println("明天---------------------------------------------------------");
        for (Task task : taskTomm) {
            System.out.println(task);
        }
    }

    @Test
    void testEqualDate() throws ParseException {
        String date = "2022-12-26";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = dateFormat.parse(date);
        Date date1 = new Date();
        String format = dateFormat.format(date1);
        Date parse1 = dateFormat.parse(format);
        System.out.println(parse1.before(parse));
        System.out.println(parse1.equals(parse));
        System.out.println(parse.compareTo(parse1));
    }
    @Test
    void testSetExpired() {
        taskMapper.setExpired(1);
    }
}