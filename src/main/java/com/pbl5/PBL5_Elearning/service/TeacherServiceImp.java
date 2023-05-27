package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Teacher;

public interface TeacherServiceImp {
    public Teacher findById(int id);

    public Teacher findByUserId(int id);
}
