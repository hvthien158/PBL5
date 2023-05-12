package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getUserDetail(@PathVariable int id){
        return new ResponseEntity<List<Map<String, ?>>>(userServiceImp.getUserById(id), HttpStatus.OK);
    }
}
