package com.brunoqueiroz.testedev.domain.playlists;

import com.brunoqueiroz.testedev.domain.musics.Music;
import com.brunoqueiroz.testedev.domain.playlists.dto.NewPlaylistDTO;
import com.brunoqueiroz.testedev.domain.playlists.dto.PlaylistDTO;
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

    public Playlist(NewPlaylistDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.musics = dto.musics().stream().map(music -> new Music(music, this)).toList();
    }
}
