package com.project.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.taskmanager.entity.TaskEntity;

import java.util.*;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, UUID>{
    
    TaskEntity findById(String id);


} 