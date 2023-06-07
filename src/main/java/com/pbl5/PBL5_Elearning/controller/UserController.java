package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
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
            String username = jwtProvider.decodeToken(token);
            Users users = userServiceImp.findUserByUsername(username);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if(bCryptPasswordEncoder.matches(changePassFormat.getOld_pass(), users.getPassword())){
              users.setPassword(bCryptPasswordEncoder.encode(changePassFormat.getNew_pass()));
              userServiceImp.updateUser(users);
              return new ResponseEntity<String>("", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Password not match", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<String>("Token invalid", HttpStatus.BAD_REQUEST);
    }

    private class ChangePassFormat{
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
}
