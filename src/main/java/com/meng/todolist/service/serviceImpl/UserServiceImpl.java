package com.meng.todolist.service.serviceImpl;

import com.meng.todolist.config.JwtConfig;
import com.meng.todolist.entity.User;
import com.meng.todolist.mapper.UserMapper;
import com.meng.todolist.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;
    @Resource
    JwtConfig jwtConfig;

    @Override
    public Map<String, String> userLogIn(String username, String password) {
        User user = userMapper.getUserByUserNameAndPassword(username,password);
        String token = jwtConfig.createToken(String.valueOf(user.getUId()));
        Map<String, String> userInfo = new HashMap<>() {{
            put("token",token);
            put("uId", String.valueOf(user.getUId()));
        }};
        return userInfo;
    }

    @Override
    public Integer userRegister(User user) {
        return userMapper.insertUser(user);
    }
}
