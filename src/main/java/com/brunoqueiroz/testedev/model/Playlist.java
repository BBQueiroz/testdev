package com.brunoqueiroz.testedev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="TB_PLAYLIST")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 70)
    private String nome;

    @Column(nullable = false, length = 70)
    private String descricao;

    @OneToMany(mappedBy = "titulo")
    private Set<Music> musics;

}
