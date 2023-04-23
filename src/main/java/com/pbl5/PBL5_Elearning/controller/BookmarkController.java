package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Bookmarks;
import com.pbl5.PBL5_Elearning.service.BlogServiceImp;
import com.pbl5.PBL5_Elearning.service.BookmarkServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    BookmarkServiceImp bookmarkServiceImp;

    @Autowired
    BlogServiceImp blogServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/{user_id}")
    public ResponseEntity<?> findMyBookmark(@PathVariable String user_id){
        return new ResponseEntity<List<Map<String, ?>>>(bookmarkServiceImp.findBookmarksByMyID(user_id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertNewBookmark(@RequestBody BookmarksFormat bookmarksFormat){
        Bookmarks bookmarks = new Bookmarks();
        bookmarks.setBlogs(blogServiceImp.findById(bookmarksFormat.getBlog_id()));
        bookmarks.setUsers(userServiceImp.findById(bookmarksFormat.getUser_id()));
        bookmarkServiceImp.insertNewBookmark(bookmarks);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }

    private static class BookmarksFormat{
        private int blog_id;
        private String user_id;

        public int getBlog_id() {
            return blog_id;
        }

        public void setBlog_id(int blog_id) {
            this.blog_id = blog_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
