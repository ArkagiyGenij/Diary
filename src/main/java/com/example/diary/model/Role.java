package com.example.diary.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("Пользователь"),
    ADMIN("Администратор"),
    TEACHER("Преподаватель");

    private final String name;

    Role(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
