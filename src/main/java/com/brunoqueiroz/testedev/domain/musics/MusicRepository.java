package com.brunoqueiroz.testedev.domain.musics;

import com.brunoqueiroz.testedev.domain.playlists.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Page<Music> findByPlaylist(Pageable pageable, Playlist playlist);
}
