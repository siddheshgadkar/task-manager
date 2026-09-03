package com.project.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.taskmanager.entity.UserInfoEntity;

import java.util.*;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoEntity, UUID>{
    
    UserInfoEntity findById(String id);

} 