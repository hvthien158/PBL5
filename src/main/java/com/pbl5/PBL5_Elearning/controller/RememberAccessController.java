package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.Teacher;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.payload.MessageResponse;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import com.pbl5.PBL5_Elearning.service.TeacherServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/me")
public class RememberAccessController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    TeacherServiceImp teacherServiceImp;

    @Autowired
    CoursesServiceImp coursesServiceImp;

    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> rememberTokenCheck(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        if(jwtProvider.validationToken(token)){
            String username = jwtProvider.decodeToken(token);
            Users users = userServiceImp.findUserByUsername(username);
            try {
                Teacher teacher = teacherServiceImp.findByUserId(users.getId());
                return new ResponseEntity<ResponseFormat>(new ResponseFormat(users.getId(), users.getUsername(), users.getEmail(), users.getGoogleId(), users.getFullName(), users.getAge(), users.getGender(), users.getAddress(), users.getPhone(), users.getAvatar(), users.getRole(), users.getListBlogs(), coursesServiceImp.findByTeacher(teacher)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Users>(users, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<MessageResponse>(new MessageResponse("Not found"), HttpStatus.BAD_REQUEST);
        }
    }

    private static class ResponseFormat{
        private int id;
        private String username;
        private String email;
        private String googleId;
        private String fullName;
        private int age;
        private int gender;
        private String address;
        private String phone;
        private String avatar;
        private int role;

        private Set<Blogs> blogs;

        private Set<Courses> courses;

        public ResponseFormat(int id, String username, String email, String googleId, String fullName, int age, int gender, String address, String phone, String avatar, int role, Set<Blogs> blogs, Set<Courses> courses) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.googleId = googleId;
            this.fullName = fullName;
            this.age = age;
            this.gender = gender;
            this.address = address;
            this.phone = phone;
            this.avatar = avatar;
            this.role = role;
            this.blogs = blogs;
            this.courses = courses;
        }

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
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public Set<Blogs> getBlogs() {
            return blogs;
        }

        public void setBlogs(Set<Blogs> blogs) {
            this.blogs = blogs;
        }

        public Set<Courses> getCourses() {
            return courses;
        }

        public void setCourses(Set<Courses> courses) {
            this.courses = courses;
        }
    }
}
