package com.meng.todolist.service;

import com.meng.todolist.entity.User;

import java.util.Map;

public interface IUserService {
    Map<String,String> userLogIn(String username, String password);
    Integer userRegister(User user);
}
