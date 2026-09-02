package com.project.taskmanager.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
    
    private String id;
    private String name;
    private Instant createdDate;
    private Instant updatedDate;
    private String createdBy;
    private String updatedBy;

}
