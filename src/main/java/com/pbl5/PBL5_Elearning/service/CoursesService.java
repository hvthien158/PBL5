package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.Teacher;
import com.pbl5.PBL5_Elearning.repository.CoursesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CoursesService implements CoursesServiceImp{
    @Autowired
    CoursesRepository coursesRepository;
    @Override
    public List<Map<String, ?>> findAll() {
        return coursesRepository.customFindAll();
    }

    @Override
    public List<Courses> find() {
        return coursesRepository.findAll();
    }

    @Override
    public Courses findById(int id) {
        return coursesRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Set<Courses> findByTeacher(Teacher teacher) {
        return coursesRepository.findAllByTeacher(teacher);
    }

    @Override
    public Courses insertNewCourse(Courses courses) {
        return coursesRepository.saveAndFlush(courses);
    }

    @Override
    public List<Map<String, ?>> searchByName(String name) {
        return coursesRepository.customSearchByName(name);
    }
}
