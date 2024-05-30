package com.brunoqueiroz.testedev.domain.musics;

import com.brunoqueiroz.testedev.domain.musics.dto.NewMusicDTO;
import com.brunoqueiroz.testedev.domain.playlists.Playlist;
import com.brunoqueiroz.testedev.domain.playlists.PlaylistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
    private final MusicRepository repository;
    private final PlaylistRepository playlistRepository;

    public MusicService(MusicRepository repository, PlaylistRepository playlistRepository) {
        this.repository = repository;
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    public Music create(NewMusicDTO dto){
        Playlist playlist = playlistRepository.findById(dto.idPlaylist()).orElseThrow();
        Music music = new Music(playlist, dto);
        repository.save(music);

        return music;
    }

    public void delete(Long id){
        Music music = repository.findById(id).orElseThrow();

        repository.delete(music);
    }
}
