package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.User_Course;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserCourseService implements UserCourseServiceImp{

    @Autowired
    UserCourseRepository userCourseRepository;

    @Override
    public List<Map<String, ?>> checkMyCourse(int user_id, int course_id) {
        return userCourseRepository.checkMyCourse(user_id, course_id);
    }

    @Override
    public User_Course registerNewCourse(User_Course user_course) {
        return userCourseRepository.saveAndFlush(user_course);
    }

    @Override
    public List<User_Course> findAllByUser(Users user) {
        return userCourseRepository.findAllByUsers(user);
    }
}
