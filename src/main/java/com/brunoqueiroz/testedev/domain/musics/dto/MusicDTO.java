package com.brunoqueiroz.testedev.domain.musics.dto;

import com.brunoqueiroz.testedev.domain.musics.Music;
import com.brunoqueiroz.testedev.domain.playlists.dto.PlaylistDTO;

public record MusicDTO(
        Long id,
        String titulo,
        String artista,
        int ano,
        String album,
        String genero,
        PlaylistDTO playlist
) {
    public MusicDTO(Music music){
        this(
                music.getId(),
                music.getTitulo(),
                music.getArtista(),
                music.getAno(),
                music.getAlbum(),
                music.getGenero(),
                new PlaylistDTO(music.getPlaylist())
        );
    }
}
