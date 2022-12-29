package com.meng.todolist.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskConditionDTO {
    private Integer lastMonthTaskCondition;
    private Integer lastWeekTaskCondition;
    private Integer lastYearTaskCondition;
}
