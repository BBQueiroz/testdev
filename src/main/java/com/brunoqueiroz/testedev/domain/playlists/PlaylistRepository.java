package com.brunoqueiroz.testedev.domain.playlists;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("SELECT p FROM Playlist p " +
            "WHERE (:nome IS NULL or p.nome = :nome)")
    Page<Playlist> findByFiltros(Pageable pageable, @Param("nome") String nome);
    boolean existsByNome(String nome);
    Playlist findByNome(String nome);
}
