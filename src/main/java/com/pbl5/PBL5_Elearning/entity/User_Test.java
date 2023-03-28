package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity(name = "user_test")
public class User_Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usertest_id")
    private int id;
    //foreign key test_id
    //foreign key user_id

    @Column(name = "score")
    private int score;

    @Column(name = "timeline")
    private Time timeline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Time getTimeline() {
        return timeline;
    }

    public void setTimeline(Time timeline) {
        this.timeline = timeline;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
