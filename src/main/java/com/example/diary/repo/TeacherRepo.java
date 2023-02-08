package com.example.diary.repo;

import com.example.diary.model.Teacher;
import com.example.diary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findByUser(User user);
}
