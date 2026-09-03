package com.project.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.project.taskmanager.dto.*;
import com.project.taskmanager.service.IUserInfoService;

@RestController("v1/users")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;
    
    @GetMapping
    public ResponseEntity<List<UserInfoResponseDTO>> getUsers(){
        return new ResponseEntity<>(userInfoService.getUsers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserInfoResponseDTO> createUser(@RequestBody UserInfoDTO userInfoDTO){
        UserInfoResponseDTO userInfoResponseDTO = userInfoService.createUser(userInfoDTO);
        return new ResponseEntity<>(userInfoResponseDTO,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserInfoResponseDTO> updateUser(@PathVariable( name = "userId" ) String userId, @RequestBody UserInfoDTO userInfoDTO){
        UserInfoResponseDTO userInfoResponseDTO = userInfoService.updateUser(userInfoDTO);
        return new ResponseEntity<>(userInfoResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserInfoResponseDTO> deleteUser(@PathVariable( name = "userId" ) String userId, @RequestBody UserInfoDTO userInfoDTO){
        userInfoService.deleteUser(userInfoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
