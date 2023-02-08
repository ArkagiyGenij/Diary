package com.example.diary.controller;

        import com.example.diary.dto.UserDto;
        import com.example.diary.model.*;
        import com.example.diary.service.*;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import java.util.List;
        import java.util.Optional;

@Controller
public class PersonalAreaController {

    private final UserService userService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final LessonService lessonService;
    private final GradeService gradeService;
    private final CustomUserDetailsService customUserDetailsService;

    public PersonalAreaController(UserService userService, TeacherService teacherService, GroupService groupService, LessonService lessonService, GradeService gradeService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.lessonService = lessonService;
        this.gradeService = gradeService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/personalarea")
    public String userArea(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "personalarea";
    }
    //    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/personalarea/{groupId}/{lessonId}")
    public String getGradeTable(@PathVariable Long groupId, @PathVariable Long lessonId, Model model) {
        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        com.example.diary.model.User user = userService.findUserByEmail(username);
        Teacher teacher = teacherService.findByUser(user);
        Optional<Group> group = groupService.getGroupById(groupId);
        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        List<Grade> grades = gradeService.findGradeByTeacherAndGroupAndLesson(teacher, group, lesson);
        model.addAttribute("grades", grades);
        return "personalarea";
    }
}
