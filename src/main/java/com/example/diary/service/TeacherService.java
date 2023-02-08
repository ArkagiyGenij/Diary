package com.example.diary.service;

import com.example.diary.model.*;
import com.example.diary.repo.TeacherRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }
    public List<Teacher> getAllTeachers(){
        return teacherRepo.findAll();
    }
    public void saveTeacher(Teacher teacher){
        this.teacherRepo.save(teacher);
    }
    public Optional<Teacher> getTeacherById(Long id){
        return teacherRepo.findById(id);
    }
    public void deleteTeacher(Long id){
        this.teacherRepo.deleteById(id);
    }
    public Teacher findByUser(User user) {
        return teacherRepo.findByUser(user);
    }
}
