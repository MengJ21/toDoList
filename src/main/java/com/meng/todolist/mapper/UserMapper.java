package com.meng.todolist.mapper;

import com.meng.todolist.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User getUserByUserNameAndPassword(String username, String password);

    User getUserByUId(String uId);
    Integer insertUser(User user);
}
