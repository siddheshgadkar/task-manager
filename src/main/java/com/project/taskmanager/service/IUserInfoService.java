package com.project.taskmanager.service;

import com.project.taskmanager.dto.UserInfoDTO;
import com.project.taskmanager.dto.UserInfoResponseDTO;
import java.util.*;


public interface IUserInfoService {

    public UserInfoResponseDTO createUser(UserInfoDTO userInfoDTO);
    public UserInfoResponseDTO updateUser(UserInfoDTO userInfoDTO);
    public void deleteUser(UserInfoDTO userInfoDTO);
    public List<UserInfoResponseDTO> getUsers();
    public UserInfoResponseDTO getUser(String username, String email);

}
