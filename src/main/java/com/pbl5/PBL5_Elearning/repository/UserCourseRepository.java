package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.User_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<User_Course, Integer> {
}
