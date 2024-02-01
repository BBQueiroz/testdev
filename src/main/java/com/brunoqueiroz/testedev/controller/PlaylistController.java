package com.brunoqueiroz.testedev.controller;

import com.brunoqueiroz.testedev.dtos.PlaylistDTO;
import com.brunoqueiroz.testedev.model.Playlist;
import com.brunoqueiroz.testedev.services.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Object> savePlaylist(@RequestBody @Valid PlaylistDTO playlistDto){
        Playlist savedPlaylist;

        try{

            savedPlaylist = playlistService.save(playlistDto);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{nome}")
                .buildAndExpand(savedPlaylist.getNome())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).body("201 Created");
    }
    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists(){
        List<Playlist> playlists = playlistService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(playlists);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Object> getOnePlaylist(@PathVariable(value = "nome") String nome){
        Playlist playlist = new Playlist();

        try{
            playlist = playlistService.findByName(nome);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(playlist);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable(value = "nome") String nome){
        try {
            playlistService.delete(nome);
        } catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 No Content");
    }
}
