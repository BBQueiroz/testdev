package com.brunoqueiroz.testedev.repository;

import com.brunoqueiroz.testedev.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public UserDetails findByLogin(String login);
}
