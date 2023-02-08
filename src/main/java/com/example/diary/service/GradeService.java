package com.example.diary.service;

import com.example.diary.model.Grade;
import com.example.diary.model.Group;
import com.example.diary.model.Lesson;
import com.example.diary.model.Teacher;
import com.example.diary.repo.GradeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private final GradeRepo gradeRepo;
    public GradeService(GradeRepo gradeRepo) {
        this.gradeRepo = gradeRepo;
    }
    public List<Grade> getAllGrades(){
        return gradeRepo.findAll();
    }
    public void saveGrade(Grade grade){
        this.gradeRepo.save(grade);
    }
    public Optional<Grade> getGradeById(Long id){
        return gradeRepo.findById(id);
    }
    public void deleteGrade(Long id){
        this.gradeRepo.deleteById(id);
    }
    public List<Grade> findGradeByTeacherAndGroupAndLesson(Teacher teacher, Optional<Group> group, Optional<Lesson> lesson){
        return gradeRepo.findGradeByTeacherAndGroupAndLesson(teacher, group, lesson);
    }
}
