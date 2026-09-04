package com.project.taskmanager.service;

import com.project.taskmanager.dto.TaskDTO;
import java.util.List;

public interface ITaskService {
    public TaskDTO createTask(TaskDTO taskDTO);
    public void deleteDask(TaskDTO taskDTO);
    public TaskDTO updateTask(TaskDTO taskDTO);
    public List<TaskDTO> getTasks();
}
