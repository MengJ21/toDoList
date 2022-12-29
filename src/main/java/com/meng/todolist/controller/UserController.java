package com.meng.todolist.controller;



import com.meng.todolist.entity.User;
import com.meng.todolist.entity.dto.UserLoginDTO;
import com.meng.todolist.model.vo.Result;
import com.meng.todolist.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService iUserService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        Map<String, String> userInfo = iUserService.userLogIn(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        return Result.ok("登录成功",userInfo);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        Integer line = iUserService.userRegister(user);
        return Result.ok("注册成功");
    }
}
