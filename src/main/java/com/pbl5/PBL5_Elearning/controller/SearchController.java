package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.service.BlogServiceImp;
import com.pbl5.PBL5_Elearning.service.CoursesServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    BlogServiceImp blogServiceImp;

    @Autowired
    CoursesServiceImp coursesServiceImp;

    @GetMapping("/{query}")
    @CrossOrigin
    public ResponseEntity<?> search(@PathVariable String query){
        query = "%" + query + "%"; //dùng để gọi trong mysql dạng tìm kiếm theo like
        return new ResponseEntity<ResponseFormat>(new ResponseFormat(userServiceImp.searchByName(query), coursesServiceImp.searchByName(query), blogServiceImp.searchByTitle(query)), HttpStatus.OK);
    }

    private static class ResponseFormat{
        private List<Map<String, ?>> users;
        private List<Map<String, ?>> courses;
        private List<Map<String, ?>> blogs;

        public ResponseFormat(List<Map<String, ?>> users, List<Map<String, ?>> courses, List<Map<String, ?>> blogs) {
            this.users = users;
            this.courses = courses;
            this.blogs = blogs;
        }

        public List<Map<String, ?>> getUsers() {
            return users;
        }

        public void setUsers(List<Map<String, ?>> users) {
            this.users = users;
        }

        public List<Map<String, ?>> getCourses() {
            return courses;
        }

        public void setCourses(List<Map<String, ?>> courses) {
            this.courses = courses;
        }

        public List<Map<String, ?>> getBlogs() {
            return blogs;
        }

        public void setBlogs(List<Map<String, ?>> blogs) {
            this.blogs = blogs;
        }
    }
}
