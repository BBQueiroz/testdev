package com.brunoqueiroz.testedev.controller;

import com.brunoqueiroz.testedev.domain.playlists.Playlist;
import com.brunoqueiroz.testedev.domain.playlists.dto.NewPlaylistDTO;
import com.brunoqueiroz.testedev.domain.playlists.dto.PlaylistDTO;
import com.brunoqueiroz.testedev.infra.security.dtos.LoginResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<NewPlaylistDTO> newPlaylistDTOJacksonTester;

    @Autowired
    private JacksonTester<PlaylistDTO> playlistDTOJacksonTester;

    @Test
    void create() throws Exception {
        // Given
        String nome = "novo nome";
        String descricao = "nova_descricao";
        NewPlaylistDTO playlistDTO = new NewPlaylistDTO(nome, descricao);
        String json = newPlaylistDTOJacksonTester.write(playlistDTO).getJson();
        String endpoint = "/lists/";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(endpoint).contentType(APPLICATION_JSON).content(json);

        // When
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    void getAll() {
    }
}