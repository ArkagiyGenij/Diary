package com.example.diary.service;

import com.example.diary.dto.UserDto;
import com.example.diary.model.Role;
import com.example.diary.model.Teacher;
import com.example.diary.model.User;
import com.example.diary.repo.TeacherRepo;
import com.example.diary.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final TeacherRepo teacherRepo;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, TeacherRepo teacherRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUserByNameOrEmail(String name, String email) {
        List<User> users = userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name, email);
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findByRoles(Role roles) {
        List<User> users = userRepository.findByRoles(roles);
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

//    @Override
//    public void addTeacherRole(User user) {
//        user.addTeacherRole();
//        userRepository.save(user);
//    }
//
//    @Override
//    public void removeTeacherRole(User user) {
//        user.removeTeacherRole();
//        userRepository.save(user);
//    }

    @Override
    public User findUserById(Long id) {
            Optional<User> optional = userRepository.findById(id);
            User userDto = null;
            if(optional.isPresent()) {
                userDto = optional.get();
            } else {
                throw new RuntimeException("Пользователь с данным ID не найден " + id +"!");
            }
            return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }



    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setId(user.getId());
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setRoleName(user.getRoles());
        return userDto;
    }
}