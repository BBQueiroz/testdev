package com.brunoqueiroz.testedev.domain.musics;

import com.brunoqueiroz.testedev.domain.playlists.Playlist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_MUSIC")
@EqualsAndHashCode(of = {"id"})
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 70)
    private String titulo;

    @Column(nullable = false, length = 70)
    private String artista;

    @Column(nullable = false, length = 70)
    private String album;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false, length = 70)
    private String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PLAYLIST_ID")
    @JsonIgnore
    private Playlist playlist;

    public Music(Music music, Playlist playlist) {
        this.titulo = music.getTitulo();
        this.artista = music.getArtista();
        this.album = music.getAlbum();
        this.ano = music.getAno();
        this.genero = music.getGenero();
        this.playlist = playlist;
    }
}