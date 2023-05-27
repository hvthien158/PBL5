package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.Courses;
import com.pbl5.PBL5_Elearning.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    @Query(nativeQuery = true, value = "call customFindAllCourse()")
    public List<Map<String, ?>> customFindAll();

    public Set<Courses> findAllByTeacher(Teacher teacher);

    @Query(nativeQuery = true, value = "call searchCourseByName(:name1)")
    public List<Map<String, ?>> customSearchByName(@PathVariable String name1);
}
