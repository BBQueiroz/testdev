package com.brunoqueiroz.testedev.repository;

import com.brunoqueiroz.testedev.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    boolean existsByNome(String nome);
    Playlist findByNome(String nome);
}
