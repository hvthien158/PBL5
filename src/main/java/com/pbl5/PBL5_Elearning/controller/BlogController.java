package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import com.pbl5.PBL5_Elearning.entity.Roles;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.service.BlogServiceImp;
import com.pbl5.PBL5_Elearning.service.RoleServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogServiceImp blogServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    RoleServiceImp roleServiceImp;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<?> findAllBlog(){
        List<BlogResponseFormat> list = new ArrayList<BlogResponseFormat>();
        List<Blogs> listBlog = blogServiceImp.findAllBlog();
        for(Blogs blog : listBlog){
            Users users = userServiceImp.findById(blog.getUsers());
            UserFormat userFormat = new UserFormat(users.getId(), users.getUsername(), users.getEmail(), users.getGoogleId(), users.getFullName(), users.getAge(), users.getGender(), users.getAddress(), users.getPhone(), users.getAvatar(), roleServiceImp.findById(users.getRole() + 1));
            BlogResponseFormat blogResponseFormat = new BlogResponseFormat(blog.getId(), blog.getTitle(), blog.getImage(), blog.getContent(), blog.getCreated_at(), userFormat);
            list.add(blogResponseFormat);
        }
        return new ResponseEntity<List<BlogResponseFormat>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getBlogDetail(@PathVariable int id){
        Blogs blogs = blogServiceImp.findById(id);
        Users users = userServiceImp.findById(blogs.getUsers());
        UserFormat userFormat = new UserFormat(users.getId(), users.getUsername(), users.getEmail(), users.getGoogleId(), users.getFullName(), users.getAge(), users.getGender(), users.getAddress(), users.getPhone(), users.getAvatar(), roleServiceImp.findById(users.getRole() + 1));
        BlogResponseFormat blogResponseFormat = new BlogResponseFormat(blogs.getId(), blogs.getTitle(), blogs.getImage(), blogs.getContent(), blogs.getCreated_at(), userFormat);
        return new ResponseEntity<BlogResponseFormat>(blogResponseFormat, HttpStatus.OK);
    }

    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> insertNewBlog(@RequestBody BlogFormat blogFormat){
        Blogs blogs = new Blogs();
        blogs.setTitle(blogFormat.getTitle());
        blogs.setImage(blogFormat.getImage());
        blogs.setContent(blogFormat.getContent());
        blogs.setCreated_at(LocalDate.parse(blogFormat.getCreated_at()));
        blogs.setUsers(userServiceImp.findById(blogFormat.getCreator()));
        blogServiceImp.saveNewBlog(blogs);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }

    private static class BlogFormat{
        private String title;
        private String image;
        private String content;
        private String created_at;
        private int creator;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }
    }

    private class BlogResponseFormat{
        private int id;
        private String title;
        private String image;
        private String content;
        private LocalDate created_at;
        private UserFormat user;

        public BlogResponseFormat(int id, String title, String image, String content, LocalDate created_at, UserFormat user) {
            this.id = id;
            this.title = title;
            this.image = image;
            this.content = content;
            this.created_at = created_at;
            this.user = user;
        }

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDate getCreated_at() {
            return created_at;
        }

        public void setCreated_at(LocalDate created_at) {
            this.created_at = created_at;
        }

        public UserFormat getUser() {
            return user;
        }

        public void setUser(UserFormat user) {
            this.user = user;
        }
    }

    private class UserFormat{
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
        private Roles role;

        public UserFormat(int id, String username, String email, String googleId, String fullName, int age, int gender, String address, String phone, String avatar, Roles role) {
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

        public Roles getRole() {
            return role;
        }

        public void setRole(Roles role) {
            this.role = role;
        }
    }
}
