package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import com.pbl5.PBL5_Elearning.service.UserCourseServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register-course")
public class UserCourseController {

    @Autowired
    UserCourseServiceImp userCourseServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    CoursesServiceImp coursesServiceImp;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/check/{course_id}")
    @CrossOrigin
    public ResponseEntity<?> checkMyCourse(@PathVariable int course_id, @RequestHeader("Authorization") String token){
        coursesServiceImp.findById(course_id);
        token = token.substring(7);
        if(jwtProvider.validationToken(token)){
            String username = jwtProvider.decodeToken(token);
            int user_id = userServiceImp.findUserByUsername(username).getId();
            List<Map<String, ?>> list = userCourseServiceImp.checkMyCourse(user_id, course_id);
            if(list.size() == 0){
                Map<String, String> res = new HashMap<>();
                res.put("registered", "false");
                return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<ResponseFormat>(new ResponseFormat("true", coursesServiceImp.findById(course_id)), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Token invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register/{course_id}")
    @CrossOrigin
    public ResponseEntity<?> registerCourse(@PathVariable int course_id, @RequestHeader("Authorization") String token){
        coursesServiceImp.findById(course_id);
        token = token.substring(7);
        if(jwtProvider.validationToken(token)){
            String username = jwtProvider.decodeToken(token);
            int user_id = userServiceImp.findUserByUsername(username).getId();
            List<Map<String, ?>> list = userCourseServiceImp.checkMyCourse(user_id, course_id);
            if(list.size() == 0){
                User_Course user_course = new User_Course();
                user_course.setUsers(userServiceImp.findById(user_id));
                user_course.setCourses(coursesServiceImp.findById(course_id));
                userCourseServiceImp.registerNewCourse(user_course);
                return new ResponseEntity<String>("Register course: Done", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Registered", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Token invalid", HttpStatus.BAD_REQUEST);
    }

    private static class ResponseFormat {
        private String registered;
        private Courses data;

        public ResponseFormat(String registered, Courses data) {
            this.registered = registered;
            this.data = data;
        }

        public String getRegistered() {
            return registered;
        }

        public void setRegistered(String registered) {
            this.registered = registered;
        }

        public Courses getData() {
            return data;
        }

        public void setData(Courses data) {
            this.data = data;
        }
    }
}
