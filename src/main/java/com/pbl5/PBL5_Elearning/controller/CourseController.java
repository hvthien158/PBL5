package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CoursesServiceImp coursesServiceImp;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<?> findAllCourse() {
        try {
            return new ResponseEntity<List<Courses>>(coursesServiceImp.find(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Not found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable int id){
        try{
            return new ResponseEntity<Courses>(coursesServiceImp.findById(id), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Not found", HttpStatus.BAD_REQUEST);
        }
    }
}
