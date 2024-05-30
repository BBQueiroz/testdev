package com.brunoqueiroz.testedev.controller;

import com.brunoqueiroz.testedev.domain.playlists.dto.NewPlaylistDTO;
import com.brunoqueiroz.testedev.domain.playlists.dto.PlaylistDTO;
import com.brunoqueiroz.testedev.domain.playlists.Playlist;
import com.brunoqueiroz.testedev.domain.playlists.PlaylistService;
import com.brunoqueiroz.testedev.domain.playlists.dto.UpdatePlaylistDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid NewPlaylistDTO dto, UriComponentsBuilder uriComponentsBuilder){
        Playlist savedPlaylist;

        try {
            savedPlaylist = playlistService.save(dto);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        PlaylistDTO playlistDTO = new PlaylistDTO(savedPlaylist);
        URI uri = uriComponentsBuilder.path("/lists/{id}").buildAndExpand(savedPlaylist.getId()).toUri();
        return ResponseEntity.created(uri).body(playlistDTO);
    }
    @GetMapping
    public ResponseEntity<Page<PlaylistDTO>> getAll(@PageableDefault Pageable pageable, @RequestParam(required = false) String nome){
        Page<Playlist> page = playlistService.findAll(pageable, nome);
        Page<PlaylistDTO> pageDTO = page.map(PlaylistDTO::new);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getOnePlaylist(@PathVariable(value = "id") Long id){
        Playlist playlist = playlistService.findById(id);
        PlaylistDTO playlistDTO = new PlaylistDTO(playlist);
        return ResponseEntity.ok(playlistDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdatePlaylistDTO dto){
        Playlist playlist = playlistService.update(id, dto);
        PlaylistDTO playlistDTO = new PlaylistDTO(playlist);

        return ResponseEntity.ok(playlistDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable(value = "id") Long id){
        playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
