package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.User_Course;

import java.util.List;
import java.util.Map;

public interface UserCourseServiceImp {
    public List<Map<String, ?>> checkMyCourse(String user_id, int course_id);

    public User_Course registerNewCourse(User_Course user_course);
}
