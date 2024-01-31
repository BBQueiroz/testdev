package com.brunoqueiroz.testedev.repository;

import com.brunoqueiroz.testedev.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
