package com.project.taskmanager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.taskmanager.dto.UserInfoDTO;
import com.project.taskmanager.dto.UserInfoResponseDTO;
import com.project.taskmanager.service.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService{

    @Override
    public UserInfoResponseDTO createUser(UserInfoDTO userInfoDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deleteUser(UserInfoDTO userInfoDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public UserInfoResponseDTO getUser(String username, String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserInfoResponseDTO> getUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserInfoResponseDTO updateUser(UserInfoDTO userInfoDTO) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
