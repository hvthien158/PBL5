package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.User_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Repository
public interface UserCourseRepository extends JpaRepository<User_Course, Integer> {
    @Query(nativeQuery = true, value = "call checkUserCourse(:user_id, :course_id)")
    public List<Map<String, ?>> checkMyCourse(@PathVariable String user_id, @PathVariable int course_id);
}
