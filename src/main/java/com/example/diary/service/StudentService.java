package com.example.diary.service;

import com.example.diary.model.Student;
import com.example.diary.model.Teacher;
import com.example.diary.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
    public void saveStudents(Student student){
        this.studentRepo.save(student);
    }
    public Optional<Student> getStudentById(Long id){
        return studentRepo.findById(id);
    }
    public void deleteStudent(Long id){
        this.studentRepo.deleteById(id);
    }
}
