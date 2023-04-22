package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Blogs;

import java.util.List;

public interface BlogServiceImp {
    public List<Blogs> findAllBlog();

    public Blogs saveNewBlog(Blogs blogs);
}
