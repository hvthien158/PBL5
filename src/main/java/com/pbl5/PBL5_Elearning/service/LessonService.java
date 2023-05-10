package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Lesson;
import com.pbl5.PBL5_Elearning.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class LessonService implements LessonServiceImp{
    @Autowired
    LessonRepository lessonRepository;
    @Override
    public Lesson insertNewLesson(Lesson lesson) {
        return lessonRepository.saveAndFlush(lesson);
    }
}
