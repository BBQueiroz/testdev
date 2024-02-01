package com.brunoqueiroz.testedev.dtos;

import com.brunoqueiroz.testedev.model.Music;
import java.util.Set;

public record PlaylistDTO(long id, String nome, String descricao, Set<Music> musics) {

}
