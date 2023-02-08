package com.example.diary.service;

import com.example.diary.model.Grade;
import com.example.diary.model.Lesson;
import com.example.diary.repo.LessonRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepo lessonRepo;

    public LessonService(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }
    public List<Lesson> getAllLessons(){
        return lessonRepo.findAll();
    }
    public void saveLessons(Lesson lesson){
        this.lessonRepo.save(lesson);
    }
    public Optional<Lesson> getLessonById(Long id){
        return lessonRepo.findById(id);
    }
    public void deleteLesson(Long id){
        this.lessonRepo.deleteById(id);
    }
}
