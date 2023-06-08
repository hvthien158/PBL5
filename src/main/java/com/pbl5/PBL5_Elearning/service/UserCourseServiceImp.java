package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.entity.Users;

import java.util.List;
import java.util.Map;

public interface UserCourseServiceImp {
    public List<Map<String, ?>> checkMyCourse(int user_id, int course_id);

    public User_Course registerNewCourse(User_Course user_course);

    public List<User_Course> findAllByUser(Users user);

    public List<Map<String, ?>> statistic();
}
