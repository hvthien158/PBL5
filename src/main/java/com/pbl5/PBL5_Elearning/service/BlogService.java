package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import com.pbl5.PBL5_Elearning.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements BlogServiceImp {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blogs> findAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Blogs saveNewBlog(Blogs blogs) {
        return blogRepository.saveAndFlush(blogs);
    }

    @Override
    public Blogs findById(int id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Error Finding Blog"));
    }
}
