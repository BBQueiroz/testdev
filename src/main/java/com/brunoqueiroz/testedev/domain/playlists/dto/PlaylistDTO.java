package com.brunoqueiroz.testedev.domain.playlists.dto;

import com.brunoqueiroz.testedev.domain.musics.Music;
import com.brunoqueiroz.testedev.domain.playlists.Playlist;

import java.util.List;
import java.util.Set;

public record PlaylistDTO(long id, String nome, String descricao, List<Music> musics) {
    public PlaylistDTO(Playlist playlist){
        this (
                playlist.getId(),
                playlist.getNome(),
                playlist.getDescricao(),
                playlist.getMusics()
        );
    }
}
