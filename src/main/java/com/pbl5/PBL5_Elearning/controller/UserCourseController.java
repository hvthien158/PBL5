package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import com.pbl5.PBL5_Elearning.service.UserCourseServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> checkMyCourse(@RequestBody UserCourseFormat userCourseFormat){
        List<Map<String, ?>> list = userCourseServiceImp.checkMyCourse(userCourseFormat.getUser_id(), userCourseFormat.getCourse_id());
        if(list.size() == 0){
            User_Course user_course = new User_Course();
            user_course.setUsers(userServiceImp.findById(userCourseFormat.getUser_id()));
            user_course.setCourses(coursesServiceImp.findById(userCourseFormat.getCourse_id()));
            userCourseServiceImp.registerNewCourse(user_course);
            return new ResponseEntity<String>("", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("", HttpStatus.CREATED);
        }
    }


    private static class UserCourseFormat{
        private String user_id;
        private int course_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }
    }
}
