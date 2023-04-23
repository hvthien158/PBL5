package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

@Entity(name = "plan")
public class Plan {

    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "create_at")
    private String create_at;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

//    public Courses getCourses() {
//        return courses;
//    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
