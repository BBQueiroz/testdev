package com.brunoqueiroz.testedev.domain.playlists.dto;

import com.brunoqueiroz.testedev.domain.musics.Music;

import java.util.List;

public record NewPlaylistDTO (String nome, String descricao, List<Music> musics){
}
