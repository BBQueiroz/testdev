package com.brunoqueiroz.testedev.model;

import com.brunoqueiroz.testedev.dtos.PlaylistDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_PLAYLIST")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 70)
    private String nome;

    @Column(nullable = false, length = 70)
    private String descricao;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<Music> musics;



    public Playlist(PlaylistDTO playlistDTO) {
        this.nome = playlistDTO.nome();
        this.descricao = playlistDTO.descricao();
        this.musics = playlistDTO.musics().stream().map(music -> new Music(music, this)).toList();
    }
}
