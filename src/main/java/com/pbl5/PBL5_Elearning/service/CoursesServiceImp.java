package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Courses;

import java.util.List;
import java.util.Map;

public interface CoursesServiceImp {
    public List<Map<String, ?>> findAll();
    public List<Courses> find();

    public Courses findById(int id);

    public Courses insertNewCourse(Courses courses);
}
