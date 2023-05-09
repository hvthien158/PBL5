package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "courses")
public class Courses {
    @Id
    @Column(name = "course_id")
    private String id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "end")
    private LocalDate end;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "totalstar")
    private double totalStar;

    @OneToMany(mappedBy = "courses")
    private Set<Lesson> lessons;

    @OneToMany(mappedBy = "courses")
    private Set<Plan> plans;

    @OneToMany(mappedBy = "courses")
    private Set<Payments> listPayments;

    @OneToMany(mappedBy = "courses")
    private Set<Announcement> listAnnouncement;

    @OneToMany(mappedBy = "courses")
    private Set<Homework> listHomework;

    //foreign key teacher_id
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "courses")
    private Set<User_Course> lisUserCourses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalStar() {
        return totalStar;
    }

    public void setTotalStar(double totalStar) {
        this.totalStar = totalStar;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }
}
