package com.brunoqueiroz.testedev.model;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("login");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

}