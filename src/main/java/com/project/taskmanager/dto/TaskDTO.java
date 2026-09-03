package com.project.taskmanager.dto;

import java.util.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskDTO extends BaseDTO{
    private String description;
    private List<TaskDTO> subTasks;
}