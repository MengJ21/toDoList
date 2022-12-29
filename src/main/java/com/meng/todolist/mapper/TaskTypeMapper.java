package com.meng.todolist.mapper;

import com.meng.todolist.entity.TaskType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskTypeMapper {
    TaskType findTypeByTypeId(String typeId);
}
