package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.payload.MessageResponse;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import com.pbl5.PBL5_Elearning.service.UserCourseServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    CoursesServiceImp coursesServiceImp;

    @Autowired
    UserCourseServiceImp userCourseServiceImp;

    @GetMapping("/course")
    @CrossOrigin
    public ResponseEntity<?> statisticCourse(@RequestHeader("Authorization") String token){
        String token1 = token.substring(7);
        if(jwtProvider.validationToken(token1)){
            String username = jwtProvider.decodeToken(token1);
            Users users = userServiceImp.findUserByUsername(username);
            if(users.getRole() != 0){
                //Not admin
                return new ResponseEntity<MessageResponse>(new MessageResponse("Not admin"), HttpStatus.BAD_REQUEST);
            } else {
                //Thống kê số lượng học viên mỗi khóa
                List<StatisticResponseFormat> listResponse = new ArrayList<StatisticResponseFormat>();
                List<Map<String, ?>> list = userCourseServiceImp.statistic();
                for(Map<String, ?> map : list){
                    StatisticResponseFormat statisticResponseFormat = new StatisticResponseFormat();
                    int id = Integer.valueOf(map.get("course_id").toString());
                    statisticResponseFormat.setCourse_id(id);
                    statisticResponseFormat.setCourse(coursesServiceImp.findById(id));
                    statisticResponseFormat.setQuantity(Long.valueOf(map.get("quantity").toString()));
                    listResponse.add(statisticResponseFormat);
                }
                return new ResponseEntity<List<StatisticResponseFormat>>(listResponse, HttpStatus.OK);
            }
        }
        return new ResponseEntity<MessageResponse>(new MessageResponse("Token invalid"), HttpStatus.BAD_REQUEST);
    }

    private static class StatisticResponseFormat{
        private int course_id;
        private long quantity;
        private Courses course;

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        public Courses getCourse() {
            return course;
        }

        public void setCourse(Courses course) {
            this.course = course;
        }
    }
}
