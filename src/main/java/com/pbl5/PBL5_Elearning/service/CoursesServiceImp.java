package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.Teacher;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CoursesServiceImp {
    public List<Map<String, ?>> findAll();
    public List<Courses> find();

    public Courses findById(int id);

    public Set<Courses> findByTeacher(Teacher teacher);

    public Courses insertNewCourse(Courses courses);

    public List<Map<String, ?>> searchByName(String name);
}
