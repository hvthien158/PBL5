package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "time")
    private Time time;

    @OneToMany(mappedBy = "test")
    private Set<User_Test> listUserTest;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Time getTime() {
        return time;
    }
}
