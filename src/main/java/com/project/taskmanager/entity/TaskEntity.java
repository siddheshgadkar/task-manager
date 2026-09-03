package com.project.taskmanager.entity;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

@Entity
@Table(name = "TASK")
public class TaskEntity extends BaseEntity {

    @Column(name = "name", length = 512, nullable = false)
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @OneToMany( mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> subTasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_task_id")
    private TaskEntity parentTask;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "guid" )
    private UserInfoEntity assignee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskEntity> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<TaskEntity> subTasks) {
        this.subTasks = subTasks;
    }

    public TaskEntity getParentTask() {
        return parentTask;
    }

    public void setParentTask(TaskEntity parentTask) {
        this.parentTask = parentTask;
    }

    public UserInfoEntity getAssignee() {
        return assignee;
    }

    public void setAssignee(UserInfoEntity assignee) {
        this.assignee = assignee;
    }

}