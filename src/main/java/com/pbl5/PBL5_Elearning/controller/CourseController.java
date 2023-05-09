package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import com.pbl5.PBL5_Elearning.service.TeacherServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CoursesServiceImp coursesServiceImp;

    @Autowired
    TeacherServiceImp teacherServiceImp;

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
    @CrossOrigin
    public ResponseEntity<?> findCourseById(@PathVariable String id){
        try{
            return new ResponseEntity<Courses>(coursesServiceImp.findById(id), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> insertNewCourse(@RequestBody CourseFormat coursesFormat){
        Courses coursesNew = new Courses();
        coursesNew.setId(coursesFormat.getId());
        coursesNew.setAvatar(coursesFormat.getAvatar());
        coursesNew.setName(coursesFormat.getName());
        coursesNew.setStart(LocalDate.parse(coursesFormat.getStart()));
        coursesNew.setEnd(LocalDate.parse(coursesFormat.getEnd()));
        coursesNew.setDescription(coursesFormat.getDescription());
        coursesNew.setPrice(coursesFormat.getPrice());
        coursesNew.setTeacher(teacherServiceImp.findById(coursesFormat.getTeacher_id()));
        coursesNew.setTotalStar(coursesFormat.getTotal_star());
        coursesServiceImp.insertNewCourse(coursesNew);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }

    private static class CourseFormat{
        private String id;
        private String avatar;
        private String name;
        private String start;
        private String end;
        private String description;
        private double price;
        private String teacher_id;

        private double total_star;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(String teacher_id) {
            this.teacher_id = teacher_id;
        }

        public double getTotal_star() {
            return total_star;
        }

        public void setTotal_star(double total_star) {
            this.total_star = total_star;
        }
    }
}
