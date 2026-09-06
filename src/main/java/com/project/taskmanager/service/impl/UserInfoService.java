package com.project.taskmanager.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.taskmanager.dto.LoginRequestDTO;
import com.project.taskmanager.dto.LoginResponseDTO;
import com.project.taskmanager.dto.UserInfoDTO;
import com.project.taskmanager.dto.UserInfoResponseDTO;
import com.project.taskmanager.entity.UserInfoEntity;
import com.project.taskmanager.repository.UserInfoRepository;
import com.project.taskmanager.security.JwtService;
import com.project.taskmanager.service.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Override
    public UserInfoResponseDTO createUser(UserInfoDTO userInfoDTO) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(userInfoDTO.getName());
        userInfoEntity.setEmail(userInfoDTO.getEmail());
        userInfoEntity.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));

        UserInfoEntity saved = userInfoRepository.save(userInfoEntity);
        return toResponseDTO(saved);
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

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UserInfoEntity userInfoEntity = userInfoRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userInfoEntity.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(userInfoEntity);
        return new LoginResponseDTO(token);
    }

    private UserInfoResponseDTO toResponseDTO(UserInfoEntity userInfoEntity) {
        UserInfoResponseDTO userInfoResponseDTO = new UserInfoResponseDTO();
        userInfoResponseDTO.setId(userInfoEntity.getId().toString());
        userInfoResponseDTO.setName(userInfoEntity.getName());
        userInfoResponseDTO.setEmail(userInfoEntity.getEmail());
        userInfoResponseDTO.setCreatedDate(userInfoEntity.getCreatedDate());
        userInfoResponseDTO.setUpdatedDate(userInfoEntity.getUpdatedDate());
        userInfoResponseDTO.setCreatedBy(userInfoEntity.getCreatedBy());
        userInfoResponseDTO.setUpdatedBy(userInfoEntity.getUpdatedBy());
        return userInfoResponseDTO;
    }

}
