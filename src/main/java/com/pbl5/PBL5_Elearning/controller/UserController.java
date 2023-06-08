package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.payload.MessageResponse;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getUserDetail(@PathVariable int id){
        return new ResponseEntity<List<Map<String, ?>>>(userServiceImp.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/changepass")
    @CrossOrigin
    public ResponseEntity<?> changePassword(@RequestBody ChangePassFormat changePassFormat, @RequestHeader("Authorization") String token){
        String token1 = token.substring(7);
        if(jwtProvider.validationToken(token1)){
            String username = jwtProvider.decodeToken(token1);
            Users users = userServiceImp.findUserByUsername(username);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if(bCryptPasswordEncoder.matches(changePassFormat.getOld_pass(), users.getPassword())){
              users.setPassword(bCryptPasswordEncoder.encode(changePassFormat.getNew_pass()));
              userServiceImp.updateUser(users);
              return new ResponseEntity<MessageResponse>(new MessageResponse("change pass successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<MessageResponse>(new MessageResponse("Password not match"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<MessageResponse>(new MessageResponse("Token invalid"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    @CrossOrigin
    public ResponseEntity<?> updateUser(@RequestBody UpdateFormat updateFormat, @RequestHeader("Authorization") String token){
        String token1 = token.substring(7);
        if(jwtProvider.validationToken(token1)){
            String username = jwtProvider.decodeToken(token1);
            Users users = userServiceImp.findUserByUsername(username);
            users.setFullName(updateFormat.getFullName());
            users.setPhone(updateFormat.getPhone());
            users.setAvatar(updateFormat.getAvatar());
            users.setGender(updateFormat.getGender());
            users.setAddress(updateFormat.getAddress());
            userServiceImp.updateUser(users);
            return new ResponseEntity<Users>(users, HttpStatus.OK);
        }
        return new ResponseEntity<MessageResponse>(new MessageResponse("Token invalid"), HttpStatus.BAD_REQUEST);
    }

    private static class ChangePassFormat{
        private String old_pass;
        private String new_pass;

        public String getOld_pass() {
            return old_pass;
        }

        public void setOld_pass(String old_pass) {
            this.old_pass = old_pass;
        }

        public String getNew_pass() {
            return new_pass;
        }

        public void setNew_pass(String new_pass) {
            this.new_pass = new_pass;
        }
    }

    private static class UpdateFormat{
        private String fullName;
        private int gender;
        private String address;
        private String phone;
        private String avatar;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
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
