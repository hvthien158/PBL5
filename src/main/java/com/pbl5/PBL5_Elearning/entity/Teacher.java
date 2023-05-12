package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.Set;

@Entity(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private String id;

    @Column(name = "degree")
    private String degree;

    @Column(name = "workplace")
    private String workplace;

    @Column(name = "slogan")
    private String slogan;

    @OneToMany(mappedBy = "teacher")
    private Set<Courses> listCourses;

    @OneToMany(mappedBy = "teacher")
    private Set<Homework> listHomework;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getUsers() {
        return users.getId();
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
