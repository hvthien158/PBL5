package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Blogs;
import com.pbl5.PBL5_Elearning.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    public List<Map<String, ?>> searchByTitle(String title) {
        return blogRepository.customSearchByTitle(title);
    }

    @Override
    public void deleteBlog(int id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blogs updateBlog(Blogs blogs) {
        return blogRepository.save(blogs);
    }
}
