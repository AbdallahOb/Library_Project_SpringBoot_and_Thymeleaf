package com.library.librarydemo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logging")
public class Logging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "action")
    private String action;
    @Column(name = "entity")
    private String entity;
    @Column(name = "role")
    private String role;
    @Column(name = "action_date")
    private LocalDateTime creationDate = LocalDateTime.now();


    public Logging (){}

    public Logging(String userName, String action, String entity, String role) {
        this.userName = userName;
        this.action = action;
        this.entity = entity;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Logging{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", action='" + action + '\'' +
                ", entity='" + entity + '\'' +
                ", role='" + role + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
