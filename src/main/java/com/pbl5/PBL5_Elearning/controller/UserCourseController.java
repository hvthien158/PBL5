package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.payload.MessageResponse;
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
                return new ResponseEntity<MessageResponse>(new MessageResponse("Register course successfully"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<MessageResponse>(new MessageResponse("Registered"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<MessageResponse>(new MessageResponse("Token invalid"), HttpStatus.BAD_REQUEST);
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
