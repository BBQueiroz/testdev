package com.brunoqueiroz.testedev.infra.security.dtos;

import com.brunoqueiroz.testedev.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}

