package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Teacher;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements TeacherServiceImp{

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserServiceImp userServiceImp;

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id).orElseThrow();
    }

    @Override
    public Teacher findByUserId(int id) {
        Users users = userServiceImp.findById(id);
        return teacherRepository.findByUsers(users).orElseThrow(()->new RuntimeException("Teacher not found"));
    }
}
