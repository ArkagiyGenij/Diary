package com.example.diary.controller;

import com.example.diary.dto.UserDto;
import com.example.diary.model.Role;
import com.example.diary.model.User;
import com.example.diary.repo.UserRepository;
import com.example.diary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "Пользователь с данной почтой уже зарегестрирован!");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("role", Role.values());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User users){
        userRepository.save(users);
//        if(users.getRoles().equals(Role.TEACHER)){
//            userService.addTeacherRole(users);
//        }
//        else{
//            userService.removeTeacherRole(users);
//        }
        return "redirect:/adminPanel";
    }

    @GetMapping("/showUserEditForm/{id}")
    public String showUserEditForm(@PathVariable(value = "id") Long id, Model model){
        User users = userService.findUserById(id);
        model.addAttribute("role", Role.values());
        model.addAttribute("users", users);
        return "update_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        this.userService.deleteUser(id);
        return "redirect:/adminPanel";
    }

}