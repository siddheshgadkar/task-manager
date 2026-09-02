package com.project.taskmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;;

@Table(name = "USER_INFO")
@Entity
public class UserInfoEntity extends BaskEntity{
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

}
