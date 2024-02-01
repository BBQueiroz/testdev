package com.brunoqueiroz.testedev.dtos;

import com.brunoqueiroz.testedev.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}

