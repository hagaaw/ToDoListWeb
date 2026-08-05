package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status_task", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTask statusTask;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    public TaskEntity(String name, StatusTask statusTask, UserEntity user) {
        this.name = name;
        this.statusTask = statusTask;
        this.user = user;
    }

    public TaskEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }
}