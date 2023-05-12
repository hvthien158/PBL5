package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Roles;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.service.RoleServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    RoleServiceImp roleServiceImp;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody RegisterFormat signUpDto){

        // add check for username exists in a DB
        if(userServiceImp.existUser(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userServiceImp.existEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = bCryptPasswordEncoder.encode(signUpDto.getPassword());

        // create user object
        Users user = new Users();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncode);
        user.setEmail(signUpDto.getEmail());
        user.setAddress(signUpDto.getAddress());
        user.setAge(signUpDto.getAge());
        user.setAvatar(signUpDto.getAvatar());
        user.setGender(signUpDto.getGender());
        user.setFullName(signUpDto.getFull_name());
        user.setGoogleId(signUpDto.getGoogle_id());
        user.setPhone(signUpDto.getPhone());

        Roles roles = roleServiceImp.findByName("User");
        user.setRole(roles);

        userServiceImp.saveNewUser(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);

    }

    private static class RegisterFormat{
        private String username;
        private String password;
        private String email;
        private String google_id;
        private String full_name;
        private int age;
        private int gender;
        private String address;
        private String phone;
        private String avatar;

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

        public String getGoogle_id() {
            return google_id;
        }

        public void setGoogle_id(String google_id) {
            this.google_id = google_id;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
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
    }
}
