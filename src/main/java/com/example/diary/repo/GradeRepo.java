package com.example.diary.repo;

import com.example.diary.model.Grade;
import com.example.diary.model.Group;
import com.example.diary.model.Lesson;
import com.example.diary.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepo extends JpaRepository<Grade, Long> {
    List<Grade> findGradeByTeacherAndGroupAndLesson(Teacher teacher, Optional<Group> group, Optional<Lesson> lesson);
}
