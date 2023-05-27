package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private int id;

    @Column(name = "total")
    private float total;

    @Column(name = "created_at")
    private LocalDate created_at;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public int getUsers() {
        return users.getId();
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
