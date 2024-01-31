package com.brunoqueiroz.testedev.services;

import com.brunoqueiroz.testedev.repository.PlaylistRepository;
import com.brunoqueiroz.testedev.model.Playlist;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public class PlaylistService {
    final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {this.playlistRepository = playlistRepository;}


    public List<Playlist> findAll() { return playlistRepository.findAll(); }


    public Playlist findById(Long id) { return playlistRepository.findById(id).get(); }


    public Playlist save(Playlist playlist) { return playlistRepository.save(playlist);}


    public String delete(Long id){
        playlistRepository.deleteById(id);
        return "playlist deleted";
    }

    public Boolean existsByName(String nome){
        return playlistRepository.existsByNome(nome);
    }

    public Playlist findByName(String nome){
        return playlistRepository.findByName(nome);
    }
}
