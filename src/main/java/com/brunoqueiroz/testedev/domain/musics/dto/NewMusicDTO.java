package com.brunoqueiroz.testedev.domain.musics.dto;

public record NewMusicDTO(
        String titulo,
        String artista,
        int ano,
        String album,
        String genero,
        Long idPlaylist
) {
}
