package com.project.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.taskmanager.dto.LoginRequestDTO;
import com.project.taskmanager.dto.LoginResponseDTO;
import com.project.taskmanager.service.IUserInfoService;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = userInfoService.login(loginRequestDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
}
