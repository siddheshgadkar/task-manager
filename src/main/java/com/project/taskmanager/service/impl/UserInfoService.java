package com.project.taskmanager.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.taskmanager.dto.UserInfoDTO;
import com.project.taskmanager.dto.UserInfoResponseDTO;
import com.project.taskmanager.entity.UserInfoEntity;
import com.project.taskmanager.repository.UserInfoRepository;
import com.project.taskmanager.service.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService{

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public UserInfoResponseDTO createUser(UserInfoDTO userInfoDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(UserInfoDTO userInfoDTO) {
        // TODO Auto-generated method stub

        Optional<UserInfoEntity> userInfoEntityOptional = userInfoRepository.findById(UUID.fromString(userInfoDTO.getId()));

        if(userInfoEntityOptional.isEmpty()){
          return;
        }

        userInfoRepository.delete(userInfoEntityOptional.get());
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
