package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.Set;

@Entity(name = "users")
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private int gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    //foreign key role_id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    @OneToMany(mappedBy = "users")
    private Set<User_Test> listUserTest;

    @OneToMany(mappedBy = "users")
    private Set<Feedback> listFeedback;

    @OneToMany(mappedBy = "users")
    private Set<Announcement> listAnnouncement;

    @OneToMany(mappedBy = "users")
    private Set<User_Course> listUserCourses;

    @OneToMany(mappedBy = "users")
    private Set<Blogs> listBlogs;

    @OneToMany(mappedBy = "users")
    private Set<Bookmarks> listBookmarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        //MySQL tự động tăng bắt đầu từ 1, bên FE xử lí mảng bắt đầu từ 0 nên trả về giá trị trừ 1 để đồng bộ
        return role.getId() - 1;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}

