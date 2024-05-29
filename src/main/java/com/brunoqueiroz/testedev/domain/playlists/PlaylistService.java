package com.brunoqueiroz.testedev.domain.playlists;

import com.brunoqueiroz.testedev.domain.playlists.dto.NewPlaylistDTO;
import com.brunoqueiroz.testedev.domain.playlists.dto.PlaylistDTO;
import com.brunoqueiroz.testedev.domain.musics.MusicRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    final PlaylistRepository playlistRepository;
    final MusicRepository musicRepository;

    public PlaylistService(PlaylistRepository playlistRepository, MusicRepository musicRepository) {this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
    }


    public Page<Playlist> findAll(Pageable pageable, String nome) { return playlistRepository.findByFiltros(pageable, nome); }


    public Playlist findById(Long id) { return playlistRepository.findById(id).orElseThrow(); }


    @Transactional
    public Playlist save(NewPlaylistDTO dto) {
        Playlist playlist = new Playlist(dto);
        playlistRepository.save(playlist);
        return playlist;
    }


    public void delete(Long id){
        if(!playlistRepository.existsById(id)) throw new NullPointerException("404 Not Found");
        playlistRepository.deleteById(id);
    }
}
