package com.example.diary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="teacher_id")
    private Teacher teacher;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="lesson_id")
    private Lesson lesson;
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="group_id")
    private Group group;
    private String mark;
}
