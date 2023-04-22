package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import com.pbl5.PBL5_Elearning.service.BlogServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogServiceImp blogServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/all")
    public ResponseEntity<?> findAllBlog(){
        return new ResponseEntity<List<Blogs>>(blogServiceImp.findAllBlog(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertNewBlog(@RequestBody BlogFormat blogFormat){
        Blogs blogs = new Blogs();
        blogs.setTitle(blogFormat.getTitle());
        blogs.setImage(blogFormat.getImage());
        blogs.setDescription(blogFormat.getDescription());
        blogs.setUsers(userServiceImp.findById(blogFormat.getCreator()));
        blogServiceImp.saveNewBlog(blogs);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }

    private static class BlogFormat{
        private String title;
        private String image;
        private String description;
        private String creator;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }
    }
}
