package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Blogs;

import java.util.List;
import java.util.Map;

public interface BlogServiceImp {
    public List<Blogs> findAllBlog();

    public Blogs saveNewBlog(Blogs blogs);

    public Blogs findById(int id);

    public List<Map<String, ?>> searchByTitle(String title);

    public void deleteBlog(int id);

    public Blogs updateBlog(Blogs blogs);

}
