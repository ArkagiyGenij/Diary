package com.example.diary.service;

import com.example.diary.dto.UserDto;
import com.example.diary.model.Role;
import com.example.diary.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    User findUserById(Long id);

    void deleteUser(Long id);

    List<UserDto> findUserByNameOrEmail(String name,String email);

    List<UserDto> findByRoles(Role roles);
//
//    void addTeacherRole(User user);
//
//    void removeTeacherRole(User user);
}