package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Teacher;
import com.pbl5.PBL5_Elearning.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements TeacherServiceImp{

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id).orElseThrow();
    }
}
