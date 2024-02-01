package com.brunoqueiroz.testedev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_MUSIC")
@ToString(exclude = "playlist")
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

    @ManyToOne
    @JoinColumn(name="playlist_id")
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