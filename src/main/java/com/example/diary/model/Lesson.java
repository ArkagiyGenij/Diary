package com.example.diary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lessonname;
//    @OneToMany(mappedBy="lesson", fetch=FetchType.EAGER)
//    private List<TeacherLesson> teacherLesson;
    @OneToMany (mappedBy="lesson", fetch=FetchType.EAGER)
    private List<Grade> grade;
}
