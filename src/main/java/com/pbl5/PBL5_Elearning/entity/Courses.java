package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    //foreign key teacher_id

    @Column(name = "totalstar")
    private String totalStar;

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

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "courses")
    private Set<User_Course> lisUserCourses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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

    public String getTotalStar() {
        return totalStar;
    }

    public void setTotalStar(String totalStar) {
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
