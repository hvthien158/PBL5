package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.*;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.payload.MessageResponse;
import com.pbl5.PBL5_Elearning.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CoursesServiceImp coursesServiceImp;

    @Autowired
    TeacherServiceImp teacherServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserCourseServiceImp userCourseServiceImp;

    @Autowired
    LessonServiceImp lessonServiceImp;

    @Autowired
    PlanServiceImp planServiceImp;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<?> findAllCourse() {
        try {
            return new ResponseEntity<List<Courses>>(coursesServiceImp.find(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<MessageResponse>(new MessageResponse("Course not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/user_id={user_id}")
    @CrossOrigin
    public ResponseEntity<?> checkCourseById(@PathVariable(name = "id") int id, @PathVariable(name = "user_id") int user_id){
        coursesServiceImp.findById(id);
        List<Map<String, ?>> list = userCourseServiceImp.checkMyCourse(user_id, id);
        if(list.size() == 0){
            return new ResponseEntity<ResponseFormat>(new ResponseFormat("false", coursesServiceImp.findById(id)), HttpStatus.OK);
        } else {
            return new ResponseEntity<ResponseFormat>(new ResponseFormat("true", coursesServiceImp.findById(id)), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> findCourseById(@PathVariable int id){
        return new ResponseEntity<Courses>(coursesServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> insertNewCourse(@RequestBody CourseFormat coursesFormat){
        Courses coursesNew = new Courses();
        modifyCourse(coursesFormat, coursesNew);
        return new ResponseEntity<MessageResponse>(new MessageResponse("new course created"), HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteCourse(@PathVariable int id, @RequestHeader("Authorization") String token){
        String token1 = token.substring(7);
        if(jwtProvider.validationToken(token1)){
            String username = jwtProvider.decodeToken(token1);
            Users users = userServiceImp.findUserByUsername(username);
            Courses courses = coursesServiceImp.findById(id);
            if(courses.getTeacher().getId() != teacherServiceImp.findByUserId(users.getId()).getId()){ //course chỉ đc xóa bởi teacher đã tạo ra nó
                return new ResponseEntity<MessageResponse>(new MessageResponse("Not own this course"), HttpStatus.OK);
            } else {
                for(Plan plan : courses.getPlans()){
                    planServiceImp.deletePlan(plan.getId());
                }
                for(Lesson lesson : courses.getLessons()){
                    lessonServiceImp.deleteLesson(lesson.getId());
                }
                coursesServiceImp.deleteCourse(id);
                return new ResponseEntity<MessageResponse>(new MessageResponse("Done"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<MessageResponse>(new MessageResponse("Token invalid"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update/{id}")
    @CrossOrigin
    public ResponseEntity<?> updateBlog(@RequestBody CourseFormat coursesFormat, @PathVariable int id, @RequestHeader("Authorization") String token){
        String token1 = token.substring(7);
        if(jwtProvider.validationToken(token1)){
            String username = jwtProvider.decodeToken(token1);
            Users users = userServiceImp.findUserByUsername(username);
            Courses courses = coursesServiceImp.findById(id);
            if(courses.getTeacher().getId() != teacherServiceImp.findByUserId(users.getId()).getId()){ //course chỉ đc xóa bởi teacher đã tạo ra nó
                return new ResponseEntity<MessageResponse>(new MessageResponse("Not own this course"), HttpStatus.OK);
            } else {
                Courses coursesNew = coursesServiceImp.findById(id);
                modifyCourse(coursesFormat, coursesNew);
                coursesServiceImp.updateCourse(coursesNew);
                return new ResponseEntity<MessageResponse>(new MessageResponse("Done"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<MessageResponse>(new MessageResponse("Token invalid"), HttpStatus.BAD_REQUEST);
    }

    private void modifyCourse(@RequestBody CourseFormat coursesFormat, Courses coursesNew) {
        coursesNew.setImage(coursesFormat.getImage());
        coursesNew.setName(coursesFormat.getName());
        coursesNew.setStart(LocalDate.parse(coursesFormat.getStart()));
        coursesNew.setEnd(LocalDate.parse(coursesFormat.getEnd()));
        coursesNew.setDescription(coursesFormat.getDescription());
        coursesNew.setPrice(coursesFormat.getPrice());
        coursesNew.setTeacher(teacherServiceImp.findById(coursesFormat.getTeacher_id()));
        coursesNew.setTotalStar(coursesFormat.getTotal_star());
        coursesServiceImp.insertNewCourse(coursesNew);
        if(coursesFormat.getLessons().length != 0){
            for(LessonFormat lessonFormat : coursesFormat.getLessons()){
                Lesson lesson = new Lesson();
                lesson.setName(lessonFormat.getName());
                lesson.setVideo(lessonFormat.getVideo());
                lesson.setGrammar(lessonFormat.getGrammar());
                lesson.setCreateAt(LocalDate.parse(lessonFormat.getCreated_at()));
                lesson.setCourses(coursesServiceImp.findById(coursesNew.getId()));
                lessonServiceImp.insertNewLesson(lesson);
            }
        }
        if(coursesFormat.getPlans().length != 0){
            for(PlanFormat planFormat : coursesFormat.getPlans()){
                Plan plan = new Plan();
                plan.setTitle(planFormat.getTitle());
                plan.setCreate_at(LocalDate.parse(planFormat.getCreated_at()));
                plan.setCourses(coursesServiceImp.findById(coursesNew.getId()));
                planServiceImp.insertNewPlan(plan);
            }
        }
    }

    private static class CourseFormat{
        private String id;
        private String image;
        private String name;
        private String start;
        private String end;
        private String description;
        private double price;
        private int teacher_id;
        private double total_star;

        private PlanFormat[] plans;

        private LessonFormat[] lessons;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public int getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(int teacher_id) {
            this.teacher_id = teacher_id;
        }

        public double getTotal_star() {
            return total_star;
        }

        public void setTotal_star(double total_star) {
            this.total_star = total_star;
        }

        public PlanFormat[] getPlans() {
            return plans;
        }

        public void setPlans(PlanFormat[] plans) {
            this.plans = plans;
        }

        public LessonFormat[] getLessons() {
            return lessons;
        }

        public void setLessons(LessonFormat[] lessons) {
            this.lessons = lessons;
        }
    }


    private static class PlanFormat{
        private String title;
        private String created_at;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }


    private static class LessonFormat{
        private String name;
        private String video;
        private String grammar;
        private String created_at;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getGrammar() {
            return grammar;
        }

        public void setGrammar(String grammar) {
            this.grammar = grammar;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
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
