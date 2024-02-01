package com.brunoqueiroz.testedev.services;

import com.brunoqueiroz.testedev.dtos.PlaylistDTO;
import com.brunoqueiroz.testedev.repository.MusicRepository;
import com.brunoqueiroz.testedev.repository.PlaylistRepository;
import com.brunoqueiroz.testedev.model.Playlist;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    final PlaylistRepository playlistRepository;
    final MusicRepository musicRepository;

    public PlaylistService(PlaylistRepository playlistRepository, MusicRepository musicRepository) {this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
    }


    public List<Playlist> findAll() { return playlistRepository.findAll(); }


    public Playlist findById(Long id) { return playlistRepository.findById(id).get(); }


    public Playlist save(PlaylistDTO playlistDto) {
        if(existsByName(playlistDto.nome())) throw new IllegalArgumentException("400 Bad Request");

        Playlist playlist = new Playlist(playlistDto);

        return playlistRepository.save(playlist);
    }


    public void delete(String name){
        if(!existsByName(name)) throw new NullPointerException("404 Not Found");
        Long id = playlistRepository.findByNome(name).getId();
        playlistRepository.deleteById(id);
    }

    public Boolean existsByName(String nome){
        return playlistRepository.existsByNome(nome);
    }

    public Playlist findByName(String nome){
        if(!existsByName(nome)) throw new NullPointerException("404 Not Found");
        return playlistRepository.findByNome(nome);
    }
}
