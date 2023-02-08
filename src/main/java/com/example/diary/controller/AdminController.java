package com.example.diary.controller;

import com.example.diary.dto.UserDto;
import com.example.diary.model.*;
import com.example.diary.repo.UserRepository;
import com.example.diary.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final GradeService gradeService;
    private final LessonService lessonService;

    public AdminController(UserServiceImpl userService, StudentService studentService, TeacherService teacherService, GroupService groupService, GradeService gradeService, LessonService lessonService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.gradeService = gradeService;
        this.lessonService = lessonService;
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/adminPanel")
    private String adminPageView(@RequestParam(required = false, defaultValue = "") String nameOrEmail,
                                 @RequestParam(required = false, defaultValue = "") String userRole,
                                 Model model){
        List<Student> studentList = studentService.getAllStudents();
        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Group> groupList = groupService.getAllGroups();
        List<Lesson> lessonList = lessonService.getAllLessons();
        List<Grade> gradeList = gradeService.getAllGrades();
        List<UserDto> users = userService.findAllUsers();
        if (nameOrEmail != null && !nameOrEmail.isEmpty()){
            users = userService.findUserByNameOrEmail(nameOrEmail, nameOrEmail);
            model.addAttribute("nameOrEmail", nameOrEmail);
        } else if (userRole != null && !userRole.isEmpty()) {
            Role role = Role.valueOf(userRole.toUpperCase());
            users = userService.findByRoles(role);
            model.addAttribute("userRole", userRole);
        } else {
            users = userService.findAllUsers();
            model.addAttribute("users", users);
        }
        model.addAttribute("studentList",studentList);
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("groupList",groupList);
        model.addAttribute("lessonList",lessonList);
        model.addAttribute("users",users);
        model.addAttribute("gradeList",gradeList);
        return "adminpanel";
    }

//    @GetMapping("/adminPanel/teacher")
//    private String adminPageTeacher(Model model){
//
//    }
}
