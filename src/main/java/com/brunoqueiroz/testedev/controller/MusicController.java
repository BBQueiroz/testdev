package com.brunoqueiroz.testedev.controller;

import com.brunoqueiroz.testedev.domain.musics.Music;
import com.brunoqueiroz.testedev.domain.musics.MusicService;
import com.brunoqueiroz.testedev.domain.musics.dto.MusicDTO;
import com.brunoqueiroz.testedev.domain.musics.dto.NewMusicDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/musics")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping
    public ResponseEntity<MusicDTO> create(@RequestBody @Valid NewMusicDTO dto, UriComponentsBuilder uriComponentsBuilder){
        Music music = musicService.create(dto);
        MusicDTO musicDTO = new MusicDTO(music);
        URI uri = uriComponentsBuilder.path("/musics/{id}").buildAndExpand(music.getId()).toUri();

        return ResponseEntity.created(uri).body(musicDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        musicService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
