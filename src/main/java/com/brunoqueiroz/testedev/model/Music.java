package com.brunoqueiroz.testedev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="TB_MUSIC")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 70)
    private String titulo;

    @Column(nullable = false, length = 70)
    private String artista;

    @Column(nullable = false, length = 70)
    private String Album;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false, length = 70)
    private String genero;
}